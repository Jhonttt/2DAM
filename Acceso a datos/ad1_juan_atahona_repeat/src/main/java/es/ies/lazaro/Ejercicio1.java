package es.ies.lazaro;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import es.ies.lazaro.model.AlumnoXML;
import es.ies.lazaro.utils.Colors;

public class Ejercicio1 {
  private static final Path XML_FILE = Path.of("altas.xml");
  private static final Path CSV_FILE = Path.of("salida", "altas.csv");
  private static final List<AlumnoXML> newAlumnos = new ArrayList<>();
  private static final List<String> CICLOS = List.of("DAM", "DAW");

  public static void main(String[] args) {
    boolean valido = validateXml();
    if (valido) {
      crearCsv();
    }
  }

  private static boolean validateXml() {
    XmlMapper mapper = new XmlMapper();
    int valido = 0, invalido = 0;
    try {
      List<AlumnoXML> alumnos = mapper.readValue(XML_FILE.toFile(), new TypeReference<List<AlumnoXML>>() {
      });
      int pos = 0;
      for (AlumnoXML a : alumnos) {
        pos++;
        String nombre = a.getNombre() == null ? "" : a.getNombre().trim();
        String ciclo = a.getCiclo() == null ? "" : a.getCiclo().trim().toUpperCase(Locale.ROOT);

        if (nombre.isEmpty() || nombre.length() > 60) {
          rechazo(pos, "el nombre tiene que tener entre 1 y 60 caracteres");
          invalido++;
          continue;
        }
        if (nombre.contains(";") || nombre.contains("\n") || nombre.contains("\r")) {
          rechazo(pos, "el nombre no puede contener punto y coma ni saltos de línea");
          invalido++;
          continue;
        }
        if (a.getEdad() < 1 || a.getEdad() > 120) {
          rechazo(pos, "la edad tiene que estar entre 1 y 120 (era " + a.getEdad() + ")");
          invalido++;
          continue;
        }
        if (!CICLOS.contains(ciclo)) {
          rechazo(pos, "el ciclo tiene que ser \"DAM\" o \"DAW\"");
          invalido++;
          continue;
        }
        valido++;
        newAlumnos.add(new AlumnoXML(nombre, a.getEdad(), ciclo));
      }
      System.out.println(Colors.WHITE + "Totales: " + (valido + invalido));
      System.out.println(Colors.GREEN + "Válidos: " + valido);
      System.out.println(Colors.RED + "Inválidos: " + invalido);
      return true;
    } catch (IOException e) {
      error("No se pudo leer el XML: " + e.getMessage() + ". CSV previo conservado.");
      return false;
    }
  }

  private static void crearCsv() {
    newAlumnos.sort(Comparator.comparing(AlumnoXML::getNombre, Collator.getInstance(Locale.of("es"))));

    try {
      Files.createDirectories(CSV_FILE.getParent());
      Path tmp = Files.createTempFile(CSV_FILE.getParent(), "altas", ".tmp");
      try {
        try (BufferedWriter bw = Files.newBufferedWriter(tmp, StandardCharsets.UTF_8)) {
          bw.write("nombre;edad;ciclo");
          for (AlumnoXML a : newAlumnos) {
            bw.newLine();
            bw.write(a.getNombre() + ";" + a.getEdad() + ";" + a.getCiclo());
          }
        }
        Files.move(tmp, CSV_FILE, StandardCopyOption.REPLACE_EXISTING);
      } finally {
        Files.deleteIfExists(tmp);
      }
    } catch (IOException e) {
      error("Error al escribir el CSV: " + e.getMessage());
    }
  }

  private static void error(String mensaje) {
    System.out.println(Colors.RED + mensaje);
  }

  private static void rechazo(int pos, String motivo) {
    System.out.println(Colors.BLUE + "Posición " + pos + ": " + Colors.RED + motivo);
  }
}
