package ad.tarea2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Ejercicio2 {
  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      System.err.println("Introduce el nombre del fichero a crear por argumentos");
      System.exit(-1);
    }

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(args[0], StandardCharsets.UTF_8))) {
      String content = "";
      boolean firstLine = true;

      do {
        System.out.print("Línea a introducir: ");
        content = BR.readLine();

        if (!content.equals("exit")) {
          if (!firstLine) bw.newLine();
          else firstLine = false;
          bw.write(content);
        }
      } while (!content.equals("exit"));

      System.out.printf("Archivo %s creado con exito.", args[0]);

    } catch (IOException e) {
      System.err.println("Error de entrada/salida: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }

    BR.close();
  }
}
