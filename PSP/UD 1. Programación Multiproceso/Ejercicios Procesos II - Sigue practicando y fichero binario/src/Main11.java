import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main11 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    System.out.print("Desde (1-1000): ");
    int desde = sc.nextInt();
    System.out.print("Hasta (1-1000): ");
    int hasta = sc.nextInt();

    if (desde < 1 || hasta > 1000 || desde > hasta) {
      System.err.println("Rango no válido");
      return;
    }

    String java = System.getProperty("java.home") + "/bin/java";
    String cp = System.getProperty("java.class.path");

    ProcessBuilder pb = new ProcessBuilder(
        java, "-cp", cp, "Sumador", "numeros.dat",
        String.valueOf(desde), String.valueOf(hasta));
    Process p = pb.start();

    // Hilo que drena stderr mientras el main lee stdout
    StringBuilder errores = new StringBuilder();
    Thread lectorErr = new Thread(() -> {
      try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
        String l;
        while ((l = br.readLine()) != null)
          errores.append(l).append('\n');
      } catch (IOException ignored) {
      }
    });
    lectorErr.start();

    String resultado;
    try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
      resultado = br.readLine();
    }

    int exit = p.waitFor();
    lectorErr.join(); // asegura que errores está completo

    if (exit != 0) {
      System.err.println("El proceso hijo falló (exit " + exit + "):\n" + errores);
      return;
    }
    System.out.println("Suma total: " + resultado);
  }
}