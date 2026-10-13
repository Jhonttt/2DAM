import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Ejecuta {@link Main} en un proceso aparte y muestra su salida en la consola.
 */
public class Executer {
  /**
   * Crea una instancia de Executer. El proceso se inicia desde {@link #main(String[])}.
   */
  public Executer() {
  }

  /**
   * Inicia el proceso de Java con las clases de la carpeta {@code bin} y le pasa
   * los argumentos recibidos. Lee su salida en UTF-8, incluidos los mensajes de
   * error, y termina con el mismo código de salida que el proceso.
   * Si falla la lectura, muestra el error, detiene el proceso y termina con código 1.
   *
   * @param args argumentos que se pasan a {@link Main} para realizar la suma
   * @throws IOException si no se puede iniciar el proceso
   * @throws InterruptedException si se interrumpe la espera a que termine el proceso
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    List<String> cmd = new ArrayList<>(List.of(
        "java", "-Dstdout.encoding=UTF-8", "-Dstderr.encoding=UTF-8",
        "-cp", "bin", "Main"));

    cmd.addAll(Arrays.asList(args));

    Process p = new ProcessBuilder(cmd).redirectErrorStream(true).start();

    try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))) {
      String linea;

      while ((linea = br.readLine()) != null) {
        System.out.println(linea);
      }
    } catch (IOException e) {
      System.err.println("Error de entrada/salida: " + e.getMessage());
      p.destroy();
      System.exit(1);
    }

    System.exit(p.waitFor());
  }
}
