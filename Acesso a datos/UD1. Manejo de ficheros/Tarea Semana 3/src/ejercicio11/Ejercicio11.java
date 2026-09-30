package ejercicio11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio11 {

  private static final String CSV_NAME = "alumnos.csv";

  public static void main(String[] args) {
    List<Alumno> alumnos = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(CSV_NAME))) {
      String line;

      br.readLine();
      while ((line = br.readLine()) != null) {
        String[] fields = line.split(";");

        Alumno a = new Alumno(Integer.parseInt(fields[0]), fields[1], Integer.parseInt(fields[2]), fields[3]);

        alumnos.add(a);

      }
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }

    for (Alumno a : alumnos) {
      System.out.println(a);
    }
  }

}
