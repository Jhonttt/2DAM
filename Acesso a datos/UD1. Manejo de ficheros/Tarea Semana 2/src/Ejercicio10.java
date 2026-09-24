import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio10 {
  public static void main(String[] args) {
    Ejercicio8.main(args);
    Path source = Path.of("datos/alumnos.txt");
    Path newName = Path.of("datos/alumnos_2026.txt");

    try {
      Files.move(source, newName);
    } catch (IOException e) {
      System.err.println("Error al crear el archivo: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
