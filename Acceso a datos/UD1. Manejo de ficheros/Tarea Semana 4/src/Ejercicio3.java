import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Locale;

public class Ejercicio3 {
  private static final String RESET = "\u001B[0m";
  private static final String RED = "\u001B[31m";
  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
  private static final Path FICHERO = Path.of("productos.dat");

  private static void readData() {
    try (DataInputStream dis = new DataInputStream(new FileInputStream(FICHERO.toFile()))) {
      ArrayList<Integer> codes = new ArrayList<>();
      double value = 0.0;

      while (dis.available() > 0) {
        int code = dis.readInt();
        String name = dis.readUTF();
        double price = dis.readDouble();
        int available = dis.readInt();

        if (!codes.contains(code)) {
          codes.add(code);
        }

        value += price * available;

        System.out.printf(Locale.ROOT,
            "Código del producto: %d%nNombre: %s%nPrecio: %.2f euros %nNúmero de unidades disponibles: %d\n---------------------\n",
            code, name,
            price, available);
      }

      System.out.println("Cantidad de productos diferentes: " + codes.size());
      System.out.printf(Locale.ROOT, "Valor total del almacén: %.2f euros", value);

    } catch (IOException e) {
      System.err.println(RED + "Error de entrada/salida: " + e.getMessage() + RESET);
    }
  }

  private static void storeData(int code, String name, double price, int available) {
    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FICHERO.toFile(), true))) {
      dos.writeInt(code);
      dos.writeUTF(name);
      dos.writeDouble(price);
      dos.writeInt(available);
    } catch (IOException e) {
      System.err.println(RED + "Error de entrada/salida: " + e.getMessage() + RESET);
    }
  }

  private static void askProducts() {
    while (true) {
      try {
        System.out.print("Código del producto (0 == salir): ");
        int code = Integer.parseInt(BR.readLine());

        if (code == 0)
          break;

        if (code < 0) {
          System.err.println(RED + "Introduce un código de producto mayor que 0, o 0 si quieres salir\n" + RESET);
          continue;
        }

        System.out.print("Nombre: ");
        String name = BR.readLine();
        if (name.isBlank()) {
          System.err.println(RED + "El nombre no puede estar vacío\n" + RESET);
          continue;
        }

        System.out.print("Precio: ");
        double price = Double.parseDouble(BR.readLine());
        if (Double.isNaN(price) || price <= 0) {
          System.err.printf("%sIntroduce una precio correcto > 0%n%s", RED, RESET);
          continue;
        }

        System.out.print("Número de unidades disponibles: ");
        int available = Integer.parseInt(BR.readLine());
        if (available < 0) {
          System.err.println(RED + "Introduce una cantidad de unidades válida > 0\n\n" + RESET);
          continue;
        }

        storeData(code, name, price, available);
        System.out.println("---------------------");
      } catch (IOException | NumberFormatException e) {
        System.err.println(RED + "Error: Formato numérico incorrecto. Por favor, inténtalo de nuevo.\n" + RESET);
      }
    }
  }

  public static void main(String[] args) {
    FICHERO.toFile().delete();
    askProducts();
    System.out.println("\n\tPRODUCTOS: ");
    readData();
  }
}
