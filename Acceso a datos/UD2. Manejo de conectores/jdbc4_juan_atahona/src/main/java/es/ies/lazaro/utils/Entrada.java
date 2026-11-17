package es.ies.lazaro.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

public final class Entrada {
  public static final List<String> CICLOS = List.of("DAM", "DAW", "ASIR");
  public static final int EDAD_MINIMA = 16;
  public static final int EDAD_MAXIMA = 99;
  public static final int NOMBRE_MAXIMO = 60;

  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

  private Entrada() {
  }

  public static int leerEntero(String prompt) throws IOException {
    while (true) {
      String s = leer(prompt);
      if (s.matches("\\d{1,9}")) {
        return Integer.parseInt(s);
      }
      error("Introduce un número entero positivo");
    }
  }

  public static String leerNombre(String prompt) throws IOException {
    while (true) {
      String s = leer(prompt);
      if (s.isEmpty()) {
        error("El nombre no puede estar vacío");
      } else if (s.length() > NOMBRE_MAXIMO) {
        error("El nombre no puede superar los " + NOMBRE_MAXIMO + " caracteres");
      } else if (!s.matches("[\\p{L} .'-]+")) {
        error("El nombre solo puede contener letras, espacios, puntos, apóstrofos y guiones");
      } else {
        return s;
      }
    }
  }

  public static int leerEdad(String prompt) throws IOException {
    while (true) {
      int edad = leerEntero(prompt);
      if (edad >= EDAD_MINIMA && edad <= EDAD_MAXIMA) {
        return edad;
      }
      error("La edad debe estar entre " + EDAD_MINIMA + " y " + EDAD_MAXIMA);
    }
  }

  public static String leerCiclo(String prompt) throws IOException {
    while (true) {
      String s = leer(prompt + " (" + String.join("/", CICLOS) + "): ").toUpperCase(Locale.ROOT);
      if (CICLOS.contains(s)) {
        return s;
      }
      error("Ciclo no válido. Opciones: " + String.join(", ", CICLOS));
    }
  }

  public static boolean confirmar(String prompt) throws IOException {
    while (true) {
      String s = leer(prompt + " (s/n): ").toLowerCase(Locale.ROOT);
      if (s.equals("s") || s.equals("si") || s.equals("sí")) {
        return true;
      }
      if (s.equals("n") || s.equals("no")) {
        return false;
      }
      error("Responde s o n");
    }
  }

  private static String leer(String prompt) throws IOException {
    System.out.print(Colors.BLUE + prompt + Colors.WHITE);
    String linea = BR.readLine();
    System.out.print(Colors.RESET);
    if (linea == null) {
      throw new IOException("La entrada se ha cerrado");
    }
    return linea.trim();
  }

  private static void error(String mensaje) {
    System.out.println(Colors.RED + mensaje + Colors.RESET);
  }
}