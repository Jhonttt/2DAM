import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class Ejercicio13 {
  private static Path p = Path.of("datos.txt");

  private static void createFileWithUTF8() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(p.toFile(), StandardCharsets.UTF_8))) {
      bw.write("Hola que tál? ño muy bien");
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  private static void readFileWithUTF8() {
    try (BufferedReader br = new BufferedReader(new FileReader(p.toFile(), StandardCharsets.UTF_8))) {
      String line;

      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  public static void main(String[] args) throws Exception {
    createFileWithUTF8();
    readFileWithUTF8();
  }
}
