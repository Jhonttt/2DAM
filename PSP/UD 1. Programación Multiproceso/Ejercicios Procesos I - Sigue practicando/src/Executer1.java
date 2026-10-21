import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Executer1 {
  public static void main(String[] args) throws IOException, InterruptedException {
    Process p = new ProcessBuilder("java", "-Dstdout.encoding=UTF-8", "-Dstderr.encoding=UTF-8",
        "-cp", "bin", "Main").redirectErrorStream(true).start();

    try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))) {
      String linea;

      while ((linea = br.readLine()) != null) {
        System.out.println(linea);
      }
    } catch (IOException e) {
      System.err.println("Error de entrada/salida: " + e.getMessage());
      p.destroy();
      System.exit(1);
    }

    System.exit(p.waitFor());
  }
}
