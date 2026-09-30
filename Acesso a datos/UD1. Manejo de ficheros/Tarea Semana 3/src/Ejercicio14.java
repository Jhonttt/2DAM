import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio14 {
  private static Path p = Path.of("datos2.txt");

  private static void createFileWithUTF8() {
    try {
      Files.writeString(p, "Hola que tál? ño muy bien", StandardCharsets.UTF_8);
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
  
  private static void readFileWithUTF8() {
    try {
      System.out.println(Files.readString(p));
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  public static void main(String[] args) throws Exception {
    createFileWithUTF8();
    readFileWithUTF8();
  }
}
