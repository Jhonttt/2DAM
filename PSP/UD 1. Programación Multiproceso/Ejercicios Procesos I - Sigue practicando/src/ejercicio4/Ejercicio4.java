package ejercicio4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import utils.Colors;

public class Ejercicio4 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    double suma = 0;
    int cantidad = 0;
    String line;

    while (true) {
      line = br.readLine();

      if (line == null) {
        System.out.println(Colors.RED + "Saliendo...");
        System.exit(0);
      }
      double valor = Double.parseDouble(line.trim());
      if (valor == 0)
        break;
      suma += valor;
      cantidad++;
    }

    if (cantidad == 0) {
      System.out.println(Colors.RED + "No se introdujo ningún valor" + Colors.RESET);
      System.exit(0);
    }
    System.out.println(Colors.BLUE + "La media es: " + Colors.GREEN
        + String.format(Locale.ROOT, "%.2f", suma / cantidad) + Colors.RESET);
    System.exit(0);

  }
}