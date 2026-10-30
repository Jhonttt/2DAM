package ejercicio3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import utils.Colors;

public class Executer3 {

  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException, InterruptedException {
    Process p = new ProcessBuilder("java", "-Dstdout.encoding=UTF-8", "-Dstderr.encoding=UTF-8",
        "-cp", "bin", "ejercicio3.Ejercicio3").start();

    try (BufferedWriter toChild = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
         BufferedReader fromChild = new BufferedReader(new InputStreamReader(p.getInputStream()))) {

      while (true) {
        System.out.print(Colors.BLUE + "Pulsa Enter para pedir un número (" + Colors.RED + "fin"
            + Colors.BLUE + " para terminar): " + Colors.WHITE);
        String line = BR.readLine();
        if (line == null || line.equalsIgnoreCase("fin")) break;

        toChild.write(line);
        toChild.newLine();
        toChild.flush();

        String numero = fromChild.readLine();
        System.out.println(Colors.BLUE + "El numero random es: " + Colors.GREEN + numero + Colors.RESET);
      }
    }

    p.destroy();
    p.waitFor();
    System.out.println(Colors.GREEN + "Programa finalizado con código de error 0 exitoso" + Colors.RESET);
  }
}