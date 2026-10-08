package ad.tarea2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class Ejercicio1a {
  private static final Path EXAMPLE = Path.of("example.txt");

  public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader(new FileReader(EXAMPLE.toFile(), StandardCharsets.UTF_8))) {
      String line;

      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      System.err.println("Error de entrada/salida: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
