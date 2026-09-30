import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Ejercicio6 {
  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
  private static final String FILE_NAME = "mensaje.txt";

  public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
      String line;
      int lines = 0;

      System.out.printf("Palabra a buscar: ");
      String word = BR.readLine();

      if (!word.isBlank()) {
        while ((line = br.readLine()) != null) {
          if (line.contains(word))
            lines++;
        }
        System.out.printf("La palabra %s sale en %d %s.%n", word, lines, lines == 1 ? "línea" : "líneas");
      } else {
        System.out.println("Palabra no válida");
      }

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
