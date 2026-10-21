package ad.tarea3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio1 {

  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

  private static void readFile(String input, String output) {
    try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(input).toFile(), StandardCharsets.UTF_8));
        BufferedWriter bw = new BufferedWriter(new FileWriter(Paths.get(output).toFile(), true))) {
      String line;
      int counter = 1; 

      while ((line = br.readLine()) != null) {
        if (counter % 2 == 0) {
          if (counter > 2) {
            bw.newLine();
          }

          bw.write(line);
        }
        counter++;
      }
    } catch (IOException e) {
      System.err.println("Error de entrada/salida: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  private static String output() {
    try {
      System.out.print("¿En qué fichero lo quieres meter?: ");
      return BR.readLine();
    } catch (IOException e) {
      System.err.println("Error de entrada/salida: " + e.getMessage());
      return null;
    }
  }

  private static String input() {
    while (true) {
      try {
        System.out.print("¿De qué fichero quieres leer solo las líneas pares?: ");
        String ruta = BR.readLine();

        if (ruta == null)
          return null;
        if (ruta.isBlank()) {
          System.err.println("Escribe una ruta.");
          continue;
        }

        Path path = Paths.get(ruta);
        if (!Files.isRegularFile(path)) {
          System.err.println("No existe el fichero: '" + path.toAbsolutePath() + "'");
        } else if (!Files.isReadable(path)) {
          System.err.println("No tienes permisos de lectura: " + path.toAbsolutePath());
        } else {
          return ruta;
        }
      } catch (IOException e) {
        System.err.println("Error de entrada/salida: " + e.getMessage());
        return null;
      } catch (InvalidPathException e) {
        System.err.println("Ruta con caracteres no válidos.");
      }
    }
  }

  public static void main(String[] args) {
    String input = input();
    if (input == null)
      System.exit(1);
    String output = output();
    readFile(input, output);
  }
}
