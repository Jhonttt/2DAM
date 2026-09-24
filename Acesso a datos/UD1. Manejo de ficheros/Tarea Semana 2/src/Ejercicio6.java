import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio6 {
  private static int times = 1;
  private static String folderName = "ArchivosYCarpetas";
  private static int rNumber = (int) (Math.random() * 2) + 1;
  private static int ficheros = 0;
  private static int directorios = 0;

  private static void randomFileOrFolder() {
    int random = (int) (Math.random() * 25) + 1;
    try {
      if (random == 1) {
        Files.createFile(Path.of(folderName, "Archivo" + times));
        return;
      }
      Files.createDirectories(Path.of(folderName, "Directorio" + times));
    } catch (IOException e) {
      System.err.println("Error al crear el archivo: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  private static void createFilesAndDirectory(int cantidad) {
    for (int i = 0; i < cantidad; i++) {
      randomFileOrFolder();
      times++;
    }
  }

  private static void fileOrDirectory() {
    File f = new File(folderName);
    File[] content = f.listFiles();

    if (content == null) {
      return;
    }

    for (File c : content) {
      times++;
      if (c.isDirectory()) {
        System.out.printf("%s.- [DIR] %s \n", times, c.getName());
        directorios++;
        continue;
      }
      System.out.printf("%s.- [FILE] %s \n", times, c.getName());
      ficheros++;
    }

    System.out.printf("FICHEROS: %d --- DIRECTORIOS: %d", ficheros, directorios);
  }

  public static void main(String[] args) {
    new File(folderName).mkdirs();
    createFilesAndDirectory(rNumber);
    times = 0;
    fileOrDirectory();
  }
}
