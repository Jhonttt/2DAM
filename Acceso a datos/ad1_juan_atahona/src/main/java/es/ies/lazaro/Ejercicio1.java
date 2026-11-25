package es.ies.lazaro;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
    int valido, invalido;
    valido = invalido = 0;
    try {
      List<AlumnoXML> alumnos = mapper.readValue(XML_FILE.toFile(), new TypeReference<List<AlumnoXML>>() {
      });
      int pos = 0;
      for (AlumnoXML a : alumnos) {
        pos++;
        if (!a.getNombre().trim().matches("[\\p{L} .'-]+")) {
          System.out.println(
              Colors.BLUE + "Posición " + pos + ": " + Colors.RED
                  + "la edad tiene que estar entre 1 y 120");
          invalido++;
          continue;
        }

        if (a.getNombre().trim().length() > 60) {
          System.out.println(
              Colors.BLUE + "Posición " + Colors.RED + pos + ": "
                  + "la edad tiene que estar entre 1 y 120");
          invalido++;
          continue;
        }

        if (a.getEdad() < 1 || a.getEdad() > 120) {
          System.out.println(
              Colors.BLUE + "Posición " + pos + ": " + Colors.RED
                  + "la edad tiene que estar entre 1 y 120");
          invalido++;
          continue;
        }

        if (!CICLOS.contains(a.getCiclo().trim().toUpperCase())) {
          System.out.println(
              Colors.BLUE + "Posición " + pos + ": " + Colors.RED
                  + "el ciclo tiene que ser \"DAM\" o \"DAW\"");
          invalido++;
          continue;
        }
        valido++;
        newAlumnos.add(new AlumnoXML(a.getNombre().trim(), a.getEdad(), a.getCiclo().trim()));
      }
      System.out.println(Colors.WHITE + "Totales: " + (invalido + valido));
      System.out.println(Colors.GREEN + "Validos: " + valido);
      System.out.println(Colors.RED + "Inválidos: " + invalido);
      return true;
    } catch (IOException e) {
      error("Error de entrada salida: " + e.getMessage());
    } catch (Exception e) {
      error("Error: " + e.getMessage());
    }
    return false;
  }

  private static void crearCsv() {
    if (Files.notExists(CSV_FILE.getParent())) {
      error("No existe el directorio: " + CSV_FILE.getParent());
    }

    if (Files.notExists(CSV_FILE)) {
      error("No existe el fichero: " + CSV_FILE);
    }

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE.toFile(), StandardCharsets.UTF_8))) {
      bw.write("nombre;edad;ciclo");

      for (AlumnoXML a : newAlumnos) {
        bw.newLine();
        bw.write(a.getNombre() + ";" + a.getEdad() + ";" + a.getCiclo());
      }

    } catch (IOException e) {
      error("Error de entrada salida: " + e.getMessage());
    } catch (Exception e) {
      error("Error: " + e.getMessage());
    }

  }

  private static void error(String mensaje) {
    System.out.println(Colors.RED + mensaje);
  }
}
