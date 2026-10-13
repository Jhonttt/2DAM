import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Locale;

public class Ejercicio4 {

  private static final String RESET = "\u001B[0m";
  private static final String RED = "\u001B[31m";
  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
  private static final Path FICHERO = Path.of("productos.dat");

  private static void readData(int codeInserted) {
    try (DataInputStream dis = new DataInputStream(new FileInputStream(FICHERO.toFile()))) {
      while (dis.available() > 0) {
        int code = dis.readInt();
        String name = dis.readUTF();
        double price = dis.readDouble();
        int available = dis.readInt();

        if (codeInserted == code) {
          System.out.printf(Locale.ROOT,
              "%nCódigo del producto: %d%nNombre: %s%nPrecio: %.2f euros %nNúmero de unidades disponibles: %d",
              code, name,
              price, available);
          return;
        }
      }

      System.out.println("No hay ningún producto con el código " + codeInserted);
    } catch (IOException e) {
      System.err.println(RED + "Error de entrada/salida: " + e.getMessage() + RESET);
    }
  }

  public static void main(String[] args) {
    int code = -1;
    while (true) {
      try {
        System.out.printf("Introduce un código a buscar: ");
        code = Integer.parseInt(BR.readLine());

        if (code < 0) {
          System.err.println(RED + "Introduce un código de producto mayor que 0, o 0 si quieres salir\n" + RESET);
          continue;
        }

        break;
      } catch (IOException | NumberFormatException e) {
        System.err.println(RED + "Error: Formato numérico incorrecto. Por favor, inténtalo de nuevo.\n" + RESET);
      }
    }

    readData(code);
  }
}
