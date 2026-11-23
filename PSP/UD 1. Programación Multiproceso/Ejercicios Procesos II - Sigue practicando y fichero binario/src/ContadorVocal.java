import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ContadorVocal {
  public static void main(String[] args) throws IOException {
    Path entrada = Path.of(args[0]);
    char vocal = Character.toLowerCase(args[1].charAt(0));
    Path salida = Path.of(args[2]);

    long total = 0;
    try (BufferedReader br = Files.newBufferedReader(entrada)) {
      int c;
      while ((c = br.read()) != -1) {
        if (Character.toLowerCase((char) c) == vocal)
          total++;
      }
    }
    Files.writeString(salida, String.valueOf(total));
  }
}