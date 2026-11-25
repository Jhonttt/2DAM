
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import utils.Colors;

public class Ejercicio2 {
  private static void error(String msg) {
    System.err.println(Colors.RED + msg);
  }

  public static void main(String[] args) {
    try {
      // Lo voy a hacer con el dir y en powershell porque en cmd no me carga
      Process p = new ProcessBuilder("powershell", "dir").start();

      try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))) {
        String l;
        while ((l = br.readLine()) != null) {
          if (l.contains("a") || l.contains("A")) {
            System.out.println(Colors.YELLOW + l);
          }
        }

        int exitCode = p.waitFor();
        System.out.println(
            Colors.PURPLE + "El proceso terminó con código: " + (exitCode == 0 ? Colors.GREEN : Colors.RED) + exitCode);
        System.exit(exitCode);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      error("Error de interrupción: " + e.getMessage());
      System.exit(1);
    } catch (IOException e) {
      error("Error de entrada/salida: " + e.getMessage());
      System.exit(1);
    } catch (Exception e) {
      error("Error general: " + e.getMessage());
      System.exit(1);
    }

  }
}
