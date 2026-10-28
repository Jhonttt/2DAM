import java.io.IOException;
import java.io.InputStream;

public class Executer1 {
  public static void main(String[] args) throws IOException, InterruptedException {
    Process p = new ProcessBuilder("java", "-Dstdout.encoding=UTF-8", "-Dstderr.encoding=UTF-8",
        "-cp", "bin", "Ejercicio1")
        .redirectInput(ProcessBuilder.Redirect.INHERIT)
        .redirectErrorStream(true)
        .start();

    try (InputStream in = p.getInputStream()) {
      in.transferTo(System.out);
    }

    System.exit(p.waitFor());
  }
}
