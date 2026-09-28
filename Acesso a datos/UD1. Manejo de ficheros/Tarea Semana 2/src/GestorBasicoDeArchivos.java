import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class GestorBasicoDeArchivos {
  private final static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

  private static void existsOrNot(boolean folder, Path p) {
    try {
      if (Files.exists(p)) {
        System.out.printf("%s %s ya existe%n", folder ? "La carpeta" : "El fichero", p);
        return;
      }
      if (folder) {
        Files.createDirectories(p);
      } else {
        Files.createFile(p);
      }
    } catch (IOException e) {
      System.err.println("Error al crear " + (folder ? "la carpeta" : "el fichero") + ": " + e.getMessage());
    }
  }

  private static void input() {
    try {
      int option = 0;
      do {
        System.out.printf("\t--¿Qué deseas hacer?--%n");
        System.out.printf("1. Crear carpeta almacén%n");
        System.out.printf("2. Crear un fichero vacío%n");
        System.out.printf("3. Listar contenidos%n");
        System.out.printf("4. Mostrar información de un fichero%n");
        System.out.printf("5. Copiar un fichero%n");
        System.out.printf("6. Renombrar/mover un fichero%n");
        System.out.printf("7. Eliminar un fichero%n");
        System.out.printf("0. Salir%n");
        System.out.printf("Opción: ");
        option = Integer.parseInt(BR.readLine());
        options(option);
      } while (option != 0);
    } catch (IOException e) {
      System.err.println("Error de input/output: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  private static Path fileName() {
    System.out.printf("Introduce el nombre del fichero vacío: ");
    String name = BR.readLine();
    return Path.of(name);
  }

  private static void options(int o) {
    switch (o) {
      case 0:
        System.out.println("Saliendo del proyecto...");
        break;
      case 1:
        existsOrNot(true, Path.of("almacen"));
        break;
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;
      case 5:
        break;
      case 6:
        break;
      case 7:
        break;

      default:
        break;
    }
  }

  public static void main(String[] args) {
    input();
  }
}
