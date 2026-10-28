package ejercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import utils.Colors;

public class Executer3 {

  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException, InterruptedException {
    while (true) {
      Process p = new ProcessBuilder("java", "-Dstdout.encoding=UTF-8", "-Dstderr.encoding=UTF-8", "-cp", "bin", "ejercicio3.Ejercicio3").start();

      try (InputStream in = p.getInputStream()) {
        in.transferTo(System.out);
      }

      System.out.print(Colors.BLUE + "Introduce " + Colors.RED +  "fin" + Colors.BLUE + " para terminar: " + Colors.WHITE);
      if (BR.readLine().equalsIgnoreCase("fin")) break;
    }
    System.out.println(Colors.GREEN + "Programa finalizado con código de error 0 exitoso" + Colors.RESET);
    System.exit(0);
  }
}
