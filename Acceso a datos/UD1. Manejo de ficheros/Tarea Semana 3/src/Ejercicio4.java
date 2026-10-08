import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio4 {
  private static final String FILE_NAME = "mensaje.txt";

  public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
      String line;

      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }

    } catch (IOException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
