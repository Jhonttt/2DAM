import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1 {
  private static final String FILE_NAME = "mensaje.txt";

  public static void main(String[] args) throws Exception {
    try (BufferedWriter bf = new BufferedWriter(new FileWriter(FILE_NAME))) {
      bf.write("Hola");
      bf.newLine();
      bf.write("qué");
      bf.newLine();
      bf.write("tal?");
    } catch (IOException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
