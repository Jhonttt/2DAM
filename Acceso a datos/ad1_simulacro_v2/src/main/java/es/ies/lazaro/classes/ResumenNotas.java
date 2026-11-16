package es.ies.lazaro.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import es.ies.lazaro.utils.Colors;

public class ResumenNotas {
  private static final Path CSV_FILE = Path.of("notas.csv");
  private static final Path TXT_FILE = Path.of("aprobados.txt");

  public static void main() throws IOException {
    if (Files.notExists(CSV_FILE)) {
      Files.createFile(CSV_FILE);
    }

    try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE.toFile(), StandardCharsets.UTF_8));
        BufferedWriter bw = new BufferedWriter(new FileWriter(TXT_FILE.toFile(), StandardCharsets.UTF_8))) {

      List<String> alumnos = br.lines().skip(1).filter(e -> !e.isBlank()).toList();
      List<String> aprobados = new ArrayList<>();
      int validate = 0;
      double totalNotes = 0;

      for (String a : alumnos) {
        String[] al = a.split(";");

        String stringId = al[0];

        if (stringId == null || stringId.isBlank()) {
          System.out.println(Colors.RED + "El id no puede estar vacío");
          continue;
        }

        if (!stringId.matches("\\d+")) {
          System.out.println(Colors.RED + "El id tiene que ser un número válido");
          continue;
        }

        int id = Integer.parseInt(stringId);

        String name = al[1];

        if (name == null || name.isBlank()) {
          System.out.println(Colors.RED + "El nombre no puede estar vacío");
          continue;
        }

        if (!name.matches("\\p{L}+")) {
          System.out.println(Colors.RED + "El nombre tiene que ser válido (solo letras)");
          continue;
        }

        String stringNote = al[2];

        if (stringNote == null || stringNote.isBlank()) {
          System.out.println(Colors.RED + "La nota no puede estar vacía");
          continue;
        }

        if (!stringNote.matches("\\d+(.\\d+)*")) {
          System.out.println(Colors.RED + "La nota tiene que ser un número válido");
          continue;
        }

        double note = Double.parseDouble(stringNote);

        validate++;
        totalNotes += note;

        if (note < 5) {
          continue;
        }

        aprobados.add(id + " - " + name + " - " + note);
      }

      boolean flag = true;
      for (String a : aprobados) {
        if (!flag) {
          bw.newLine();
        }

        bw.write(a);

        flag = false;
      }

      System.out.printf(Locale.ROOT, "%s%d registros válidos, %d aprobados y media %.2f%n", Colors.ORANGE, validate,
          aprobados.size(),
          (totalNotes / validate));
    }
  }
}
