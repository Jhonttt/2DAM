import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio11 {
  private static void createOrNot() {
    int random = (int) (Math.random() * 2) + 1;
    if (random == 1) Ejercicio8.main(null);
  }
  
  public static void main(String[] args) {
    Path p = Path.of("datos/alumnos.txt");

    // if (Files.exists(p)) Files.delete(p);
    
  }
}
