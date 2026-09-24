import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Ejercicio9 {
  public static void main(String[] args) {
    Ejercicio8.main(args);
    Path receiver = Path.of("copias/alumnos_backup.txt");
    Path emisor = Path.of("datos/alumnos.txt");

    try {
      if (!Files.exists(receiver.getParent())) Files.createDirectories(receiver.getParent());
      Files.copy(emisor, receiver, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      System.err.println("Error al crear el archivo: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
