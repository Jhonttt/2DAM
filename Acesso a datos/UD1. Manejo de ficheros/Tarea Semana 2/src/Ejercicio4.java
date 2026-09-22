import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio4 {
  private static String[] directories = { "datos", "copias", "informes" };

  private static void createWithFile() {
    for (String dir : directories) {
      new File("proyecto/" + dir).mkdirs();
    }
  }

  private static void createWithPathAndFiles() {
    for (String dir : directories) {
      try {
        Files.createDirectories(Path.of("proyecto", dir));
      } catch (IOException e) {
        System.err.println("Error al crear el archivo: " + e.getMessage());
      } catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
      }
    }
  }

  public static void main(String[] args) {
    createWithFile();
    createWithPathAndFiles();
  }
}
