import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio8 {
  public static void main(String[] args) {
    Path p = Path.of("datos/alumnos.txt");

    try {
      if (!Files.exists(p.getParent())) Files.createDirectories(p.getParent());
      if (!Files.exists(p)) Files.createFile(p);
    } catch (IOException e) {
      System.err.println("Error al crear el archivo: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }

  }
}
