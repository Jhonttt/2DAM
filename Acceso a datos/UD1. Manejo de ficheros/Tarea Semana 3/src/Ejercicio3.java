import java.io.FileReader;
import java.io.IOException;

public class Ejercicio3 {
  private static final String FILE_NAME = "mensaje.txt";

  public static void main(String[] args) {
    try (FileReader fr = new FileReader(FILE_NAME)) {
      int character;
      while ((character = fr.read()) != -1) {
        System.out.print((char) character);
      }
    } catch (IOException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
