package es.instituto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) {
    try {
      System.out.println("Ejercicio 2: ");
      AlumnoDAO.selectAlumnos().forEach(System.out::println);

      String ciclo = pedirCiclo();
      int edad = pedirEdad();

      System.out.println("Ejercicio 3: ");
      AlumnoDAO.selectAlumnosByCicle(ciclo).forEach(System.out::println);

      System.out.println("Ejercicio 4: ");
      AlumnoDAO.selectAlumnosByCicleAndAge(ciclo, edad).forEach(System.out::println);

      System.out.println("Ejercicio 5: ");
      AlumnoDAO.selectSummaryStudents();
    } catch (IOException e) {
      System.err.println("Error de entrada/salida: " + e.getMessage());
    }
  }

  private static String pedirCiclo() throws IOException {
    while (true) {
      System.out.print("Ciclo (DAM/DAW): ");
      String ciclo = BR.readLine();

      if (ciclo == null) {
        throw new IOException("Entrada finalizada");
      }

      if (ciclo.equalsIgnoreCase("DAM") || ciclo.equalsIgnoreCase("DAW")) {
        return ciclo.toUpperCase();
      }

      System.out.println("Ciclo no válido.");
    }
  }

  private static int pedirEdad() throws IOException {
    while (true) {
      System.out.print("Edad (0-120): ");
      String entrada = BR.readLine();

      if (entrada == null) {
        throw new IOException("Entrada finalizada");
      }

      try {
        int edad = Integer.parseInt(entrada.trim());
        if (edad >= 0 && edad <= 120) {
          return edad;
        }
      } catch (NumberFormatException e) {
      }

      System.out.println("Edad no válida.");
    }
  }
}
