package ad.tarea3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio2 {
  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

  private static void interleave(Path f1, Path f2, Path salida) {
    try (BufferedReader br1 = Files.newBufferedReader(f1, StandardCharsets.UTF_8);
        BufferedReader br2 = Files.newBufferedReader(f2, StandardCharsets.UTF_8);
        BufferedWriter bw = Files.newBufferedWriter(salida, StandardCharsets.UTF_8)) {

      String l1 = br1.readLine();
      String l2 = br2.readLine();

      while (l1 != null || l2 != null) {
        if (l1 != null) {
          bw.write(l1);
          bw.newLine();
          l1 = br1.readLine();
        }
        if (l2 != null) {
          bw.write(l2);
          bw.newLine();
          l2 = br2.readLine();
        }
      }

      System.out.println("Fichero generado: " + salida);
    } catch (IOException e) {
      System.err.println("Error de entrada/salida: " + e.getMessage());
    }
  }

  private static Path askWriting(String mensaje, Path... lecturas) {
    while (true) {
      try {
        System.out.print(mensaje);
        String ruta = BR.readLine();

        if (ruta == null)
          return null;
        if (ruta.isBlank()) {
          System.err.println("Escribe una ruta.");
          continue;
        }

        Path path = Paths.get(ruta.trim()).toAbsolutePath().normalize();
        Path carpeta = path.getParent();

        boolean esLectura = false;
        for (Path l : lecturas) {
          if (l.toAbsolutePath().normalize().equals(path)) {
            esLectura = true;
            break;
          }
        }

        if (esLectura) {
          System.err.println("No puede ser uno de los ficheros de lectura.");
        } else if (Files.isDirectory(path)) {
          System.err.println("Esa ruta es una carpeta, no un fichero.");
        } else if (carpeta != null && !Files.isDirectory(carpeta)) {
          System.err.println("No existe la carpeta: " + carpeta);
        } else if (Files.exists(path) && !Files.isWritable(path)) {
          System.err.println("No tienes permisos de escritura: " + path);
        } else {
          return path;
        }
      } catch (IOException e) {
        System.err.println("Error de entrada/salida: " + e.getMessage());
        return null;
      } catch (InvalidPathException e) {
        System.err.println("Ruta con caracteres no válidos.");
      }
    }
  }

  private static Path askReading(String mensaje) {
    while (true) {
      try {
        System.out.print(mensaje);
        String ruta = BR.readLine();

        if (ruta == null)
          return null;
        if (ruta.isBlank()) {
          System.err.println("Escribe una ruta.");
          continue;
        }

        Path path = Paths.get(ruta.trim());

        if (!Files.isRegularFile(path)) {
          System.err.println("No existe el fichero: '" + path.toAbsolutePath() + "'");
        } else if (!Files.isReadable(path)) {
          System.err.println("No tienes permisos de lectura: " + path.toAbsolutePath());
        } else {
          return path;
        }
      } catch (IOException e) {
        System.err.println("Error de entrada/salida: " + e.getMessage());
        return null;
      } catch (InvalidPathException e) {
        System.err.println("Ruta con caracteres no válidos.");
      }
    }
  }

  public static void main(String[] args) {
    Path f1 = askReading("Primer fichero de lectura: ");
    if (f1 == null)
      System.exit(1);

    Path f2 = askReading("Segundo fichero de lectura: ");
    if (f2 == null)
      System.exit(1);

    Path salida = askWriting("Fichero de salida: ", f1, f2);
    if (salida == null)
      System.exit(1);

    interleave(f1, f2, salida);

  }
}
