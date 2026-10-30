package ejercicio4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import utils.Colors;

public class Executer4 {

  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException, InterruptedException {
    Process p = new ProcessBuilder("java", "-Dstdout.encoding=UTF-8", "-Dstderr.encoding=UTF-8", "-cp", "bin",
        "ejercicio4.Ejercicio4").start();

    try (BufferedWriter toChild = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
      while (true) {
        System.out.print(Colors.BLUE + "Introduce un valor (" + Colors.RED + "0" + Colors.BLUE
            + " para terminar): " + Colors.WHITE);
        String line = BR.readLine();
        if (line == null)
          break;

        double valor;
        try {
          valor = Double.parseDouble(line.trim());
        } catch (NumberFormatException e) {
          System.out.println(Colors.RED + "Valor no válido" + Colors.RESET);
          continue;
        }

        toChild.write(String.valueOf(valor));
        toChild.newLine();
        toChild.flush();

        if (valor == 0)
          break;
      }
    }

    try (BufferedReader fromChild = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
      String linea;
      while ((linea = fromChild.readLine()) != null) {
        System.out.println(linea);
      }
    }

    p.waitFor();
    System.out.print(Colors.RESET);
  }
}