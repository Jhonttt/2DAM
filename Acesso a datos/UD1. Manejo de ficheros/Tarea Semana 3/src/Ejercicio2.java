import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio2 {
  private static final String FILE_NAME = "mensaje.txt";

  public static void main(String[] args) {
    try (BufferedWriter bf = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
      bf.newLine();
      bf.write("Nueva línea");
    } catch (IOException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
