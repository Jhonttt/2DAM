import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.Colors;

public class LanzadorInversa {
  private static final String SON = Inversa.class.getName();
  private static final int ERR_CODE = 1;

  public static void main(String[] args) {
    String cp = System.getProperty("java.class.path");
    List<String> command = new ArrayList<>(List.of("java", "-cp", cp, SON));

    command.addAll(Arrays.asList(args));

    try {
      Process p = new ProcessBuilder(command).redirectErrorStream(true).start();

      try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))) {
        String l;

        while ((l = br.readLine()) != null) {
          System.out.println(l);
          if (l.equalsIgnoreCase(Colors.RED + "Introduce argumentos")) {
            continue;
          }
          System.out.println(Colors.WHITE + "Con " + (l.length() - 7) + " caracteres");
        }

        br.close();
        int exitCode = p.waitFor();
        System.out.println(Colors.WHITE + "Se ha terminado con un código " + (exitCode == 0 ? Colors.GREEN : Colors.RED) + exitCode);
        System.exit(exitCode);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      error("Error de interrupción: " + e.getMessage());
      System.exit(ERR_CODE);
    } catch (IOException e) {
      error("Error de entrada/salida: " + e.getMessage());
      System.exit(ERR_CODE);
    }
  }

  private static void error(String msg) {
    System.err.println(Colors.RED + msg);
  }
}
