import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio5 {
  private static final String FILE_NAME = "mensaje.txt";

  public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
      String line;
      int lines = 0, characters = 0, emptyLines = 0;

      while ((line = br.readLine()) != null) {
        lines++;
        characters += line.length();
        if (line.isBlank()) emptyLines++;
      }

      System.out.printf("Número de líneas: %d%n", lines);
      System.out.printf("Número total de caracteres: %d%n", characters);
      System.out.printf("Número de líneas vacías: %d%n", emptyLines);

    } catch (IOException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
