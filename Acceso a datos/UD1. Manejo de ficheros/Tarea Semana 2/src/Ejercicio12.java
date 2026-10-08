import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio12 {
  public static void main(String[] args) {
    Path p = Path.of("inexistente/dato.txt");

    try {
      Files.createFile(p);
    } catch (IOException e) {
      System.err.println("Error al crear el archivo: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
