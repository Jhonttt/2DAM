import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class GestorBasicoDeArchivos {
  private final static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
  private final static Path ALMACEN = Path.of("almacen");

  private static Path fileName(String mensaje) throws IOException {
    System.out.printf("%s: ", mensaje);
    String entrada = BR.readLine();
    if (entrada == null) {
      throw new IOException("no se ha recibido ninguna entrada");
    }

    Path base = ALMACEN.toAbsolutePath().normalize();
    Path p = base.resolve(entrada.trim()).normalize();

    if (!p.startsWith(base) || p.equals(base)) {
      throw new IOException("la ruta '" + entrada.trim() + "' queda fuera de " + ALMACEN);
    }
    return p;
  }

  private static boolean exists(Path p) {
    if (Files.exists(p)) {
      return true;
    }
    System.out.printf("%s no existe%n", p);
    return false;
  }

  private static void existsOrNot(boolean folder) {
    if (!folder && !exists(ALMACEN)) {
      return;
    }
    try {
      Path p = folder ? ALMACEN : fileName("Nombre del fichero");
      if (Files.exists(p)) {
        System.out.printf("%s %s ya existe%n", folder ? "La carpeta" : "El fichero", p);
        return;
      }
      if (folder) {
        Files.createDirectory(p);
      } else {
        Files.createFile(p);
      }
      System.out.printf("%s creado%n", p);
    } catch (IOException e) {
      System.err.println("Error " + (folder ? "al crear la carpeta" : "al crear el fichero") + ": " + e.getMessage());
    }
  }

  private static void listContent() {
    int times = 0;
    File f = ALMACEN.toFile();
    File[] content = f.listFiles();

    if (content == null) {
      System.out.printf("%s no existe%n", ALMACEN);
      return;
    }

    for (File c : content) {
      times++;
      if (c.isDirectory()) {
        System.out.printf("%s.- [DIR] %s%n", times, c.getName());
        continue;
      }
      System.out.printf("%s.- [FILE] %s%n", times, c.getName());
    }
  }

  private static void fileInfo() {
    try {
      Path p = fileName("Nombre del fichero");
      if (!exists(p)) {
        return;
      }
      System.out.printf("Nombre: %s%n", p.getFileName());
      System.out.printf("Ruta: %s%n", p.toAbsolutePath());
      System.out.printf("Tamaño: %d bytes%n", Files.size(p));
      System.out.printf("Última modificación: %s%n", Files.getLastModifiedTime(p));
    } catch (IOException e) {
      System.err.println("Error al mostrar la información del fichero: " + e.getMessage());
    }
  }

  private static void copyOrMove(boolean copy) {
    try {
      Path origen = fileName("Fichero de origen");
      if (!exists(origen)) {
        return;
      }
      Path destino = fileName("Fichero de destino");
      if (copy) {
        Files.copy(origen, destino);
      } else {
        Files.move(origen, destino);
      }
      System.out.printf("%s -> %s%n", origen, destino);
    } catch (IOException e) {
      System.err.println("Error " + (copy ? "al copiar el fichero" : "al mover el fichero") + ": " + e.getMessage());
    }
  }

  private static void deleteFile() {
    try {
      Path p = fileName("Fichero a eliminar");
      if (!exists(p)) {
        return;
      }
      Files.delete(p);
      System.out.printf("%s eliminado%n", p);
    } catch (IOException e) {
      System.err.println("Error al eliminar el fichero: " + e.getMessage());
    }
  }

  private static void options(int o) {
    switch (o) {
      case 0 -> System.out.println("Saliendo del proyecto...");
      case 1 -> existsOrNot(true);
      case 2 -> existsOrNot(false);
      case 3 -> listContent();
      case 4 -> fileInfo();
      case 5 -> copyOrMove(true);
      case 6 -> copyOrMove(false);
      case 7 -> deleteFile();

      default -> System.out.println("Opción no válida");
    }
  }

  private static void input() {
    int option;
    do {
      System.out.printf("%n\t--¿Qué deseas hacer?--%n");
      System.out.printf("1. Crear carpeta almacén%n");
      System.out.printf("2. Crear un fichero vacío%n");
      System.out.printf("3. Listar contenidos%n");
      System.out.printf("4. Mostrar información de un fichero%n");
      System.out.printf("5. Copiar un fichero%n");
      System.out.printf("6. Renombrar/mover un fichero%n");
      System.out.printf("7. Eliminar un fichero%n");
      System.out.printf("0. Salir%n");
      System.out.printf("Opción: ");
      try {
        option = Integer.parseInt(BR.readLine().trim());
        options(option);
      } catch (NumberFormatException e) {
        System.err.println("Introduce un número");
        option = -1;
      } catch (IOException e) {
        System.err.println("Error al leer la opción del menú: " + e.getMessage());
        option = 0;
      }
    } while (option != 0);
  }

  public static void main(String[] args) {
    input();
  }
}