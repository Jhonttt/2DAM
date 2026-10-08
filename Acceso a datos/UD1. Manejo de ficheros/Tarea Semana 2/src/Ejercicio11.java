import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio11 {
  private static void createOrNot() {
    int random = (int) (Math.random() * 2) + 1;
    if (random == 1) Ejercicio8.main(null);
  }

  public static void main(String[] args) {
    Path p = Path.of("datos/alumnos.txt");

    createOrNot();

    try {
      if (Files.exists(p)) Files.delete(p);
      else System.out.println("El fichero a borrar no existe");
    } catch (IOException e) {
      System.err.println("Error al crear el archivo: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }

  }
}
