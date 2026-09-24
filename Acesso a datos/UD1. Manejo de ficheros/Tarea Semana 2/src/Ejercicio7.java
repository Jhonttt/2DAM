import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio7 {
  public static void main(String[] args) {
    Path p = Path.of("datos/alumnos.txt");

    try {
      Files.createDirectories(p.getParent());
      if (!Files.exists(p)) Files.createFile(p);
    } catch (IOException e) {
      System.err.println("Error al crear el archivo: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }

    System.out.printf("Ruta original: %s\n", p);
    System.out.printf("Ruta absoluta: %s\n", p.toAbsolutePath());
    System.out.printf("Nombre: %s\n", p.getName(1));
    System.out.printf("Directorio padre: %s\n", p.getParent());
  }
}
