import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Sumador {
  public static void main(String[] args) throws IOException {
    Path fichero = Path.of(args[0]);
    int desde = Integer.parseInt(args[1]);
    int hasta = Integer.parseInt(args[2]);

    long suma = 0;
    try (BufferedReader br = Files.newBufferedReader(fichero)) {
      String linea;
      int n = 0;
      while ((linea = br.readLine()) != null) {
        n++;
        if (n < desde)
          continue;
        if (n > hasta)
          break; // no leemos el resto
        suma += Long.parseLong(linea.trim());
      }
    }
    System.out.println(suma); // el padre lo lee por stdout
  }
}