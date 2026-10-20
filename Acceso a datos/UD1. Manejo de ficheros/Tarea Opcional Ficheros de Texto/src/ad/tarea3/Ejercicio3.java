package ad.tarea3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Ejercicio3 {
  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

  private static Path askFile(String mensaje) {
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
        } else if (!Files.isWritable(path)) {
          System.err.println("No tienes permisos de escritura: " + path.toAbsolutePath());
        } else if (Files.size(path) == 0) {
          System.err.println("El fichero está vacío.");
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

  private static boolean endsWithNewLine(Path path) throws IOException {
    try (RandomAccessFile raf = new RandomAccessFile(path.toFile(), "r")) {
      if (raf.length() == 0)
        return true;
      raf.seek(raf.length() - 1);
      return raf.read() == '\n';
    }
  }

  private static void appendFromKeyboard(Path path) {
    try {
      boolean faltaSalto = !endsWithNewLine(path);

      try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
          StandardOpenOption.APPEND)) {

        if (faltaSalto)
          bw.newLine();

        System.out.print("Escribe líneas (\"salir\" para terminar): ");
        String linea;
        while ((linea = BR.readLine()) != null && !linea.equals("salir")) {
          bw.write(linea);
          bw.newLine();
        }
      }

      System.out.println("Contenido añadido a " + path.toAbsolutePath());
    } catch (IOException e) {
      System.err.println("Error de entrada/salida: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    Path fichero = askFile("Fichero al que añadir texto: ");
    if (fichero == null)
      System.exit(1);

    appendFromKeyboard(fichero);
  }
}