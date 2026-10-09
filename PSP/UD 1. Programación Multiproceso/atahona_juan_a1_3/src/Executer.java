import java.io.*;
import java.nio.charset.StandardCharsets;

public class Executer {
  private static final int ARG1 = 0;
  private static final int ARG2 = 1;

  private static final String RESET = "\u001B[0m";
  private static final String RED = "\u001B[31m";

  public static void main(String[] args) throws IOException, InterruptedException {

    if (args.length != 2) {
      System.out.println(RED + "Introduce 2 números por argumento" + RESET);
      System.exit(2);
    }

    Process p = new ProcessBuilder("java", "-Dstdout.encoding=UTF-8", "-cp", "bin", "Main", args[ARG1], args[ARG2]).start();

    try (BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))) {
      String linea;

      while ((linea = bf.readLine()) != null) {
        System.out.println(linea);
      }
    } catch (IOException e) {
      System.err.println("Error de entrada/salida: " + e.getMessage());
    }
  }
}
