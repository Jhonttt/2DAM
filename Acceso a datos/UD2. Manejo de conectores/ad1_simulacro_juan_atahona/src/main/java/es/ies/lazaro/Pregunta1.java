package es.ies.lazaro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

import es.ies.lazaro.utils.Colors;

public class Pregunta1 {
  private static final Path CSV_FILE = Path.of("notas.csv");
  private static final Path CSV_FILE_PASSED = Path.of("aprobados.txt");

  public static void readNotas() {
    boolean first = true;
    int validates = 0;
    int passed = 0;
    double totalNotes = 0.0;
    try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE.toFile(), StandardCharsets.UTF_8));
        BufferedWriter bw = new BufferedWriter(
            new FileWriter(CSV_FILE_PASSED.toFile(), StandardCharsets.UTF_8))) {
      List<String> file = br.lines().skip(1).filter(l -> !l.isBlank()).toList();

      for (String f : file) {
        String[] l = f.split(";", -1);
        String name = l[1];

        if (name == null || name.isBlank()) {
          System.out.println(Colors.RED + "El nombre del alumno esta vacío");
          continue;
        }

        if (!l[0].matches("\\d+")) {
          System.out.println(Colors.RED + "Alumno " + l[1] + " tiene una id no numérica");
          continue;
        }

        int id = Integer.parseInt(l[0]);

        if (id < 0) {
          System.out.println(Colors.RED + "Alumno " + l[1] + " tiene una id menor a 0");
          continue;
        }

        if (!l[2].matches("\\d+.\\d+")) {
          System.out.println(Colors.RED + "Alumno " + l[1] + " tiene una nota no numérica");
          continue;
        }

        double note = Double.parseDouble(l[2]);

        if (note < 0 || note > 10) {
          System.out.println(Colors.RED + "Alumno " + l[1] + " tiene una nota fuera del rango (0-10)");
          continue;
        }

        if (note >= 5) {
          if (first) {
            first = false;
          }
          bw.write(id + " - " + name + " - " + note);
          bw.newLine();
          passed++;
        }

        totalNotes += note;
        validates++;
      }

      if (first) {
        System.out.println(Colors.RED + "No se ha creado " + CSV_FILE_PASSED + " ya que nadie aprobó");
      }

      if (validates == 0) {
        System.out.println(Colors.RED + "No hay registros válidos");

      } else {
        System.out.println(Colors.PURPLE + validates + Colors.BLUE + " registoros válidos, " + Colors.GREEN + passed
            + Colors.BLUE + " aprobados y media " + Colors.ORANGE + (totalNotes / validates));
      }

    } catch (IOException e) {
      System.out.println(Colors.RED + "Error de entrada/salida: " + e.getMessage());
    }
  }
}
