import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main1 {
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
    pb.redirectErrorStream(true); // stderr -> stdout, un solo stream que leer

    Process p = pb.start();

    // Leemos TODO lo que saca el hijo (resultado o mensaje de error)
    StringBuilder salida = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
      String linea;
      while ((linea = br.readLine()) != null) {
        salida.append(linea).append(System.lineSeparator());
      }
    }

    int exit = p.waitFor();
    if (exit != 0) {
      System.err.println("El proceso hijo falló (exit " + exit + "):");
      System.err.print(salida);
      return;
    }
    System.out.println("Suma total: " + salida.toString().trim());
  }
}