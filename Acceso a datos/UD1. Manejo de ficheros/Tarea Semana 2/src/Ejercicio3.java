import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio3 {
  public static void main(String[] args) {
    Path doc = Path.of("documentos");

    try {
      if (!Files.exists(doc)) {
        Files.createDirectories(doc);
        System.out.printf("Carpeta %s creada correctamente.", doc.getName(0));
        return;
      } 
      System.out.printf("Ya existe la carpeta %s.", doc.getName(0));
    } catch (IOException e) {
      System.err.println("Error al crear el archivo: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
