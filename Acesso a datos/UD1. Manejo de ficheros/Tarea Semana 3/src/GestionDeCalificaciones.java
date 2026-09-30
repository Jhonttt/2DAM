import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class GestionDeCalificaciones {
  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
  private static final Path CALIFICACIONES = Path.of("calificaciones.csv");
  private static int NAME = 1;
  private static int MODULE = 2;

  private static int NOTE = 3;

  private static void createCalification() {
    if (Files.exists(CALIFICACIONES))
      return;

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(CALIFICACIONES.toString(), StandardCharsets.UTF_8))) {
      bw.write("id;nombre;modulo;nota");
    } catch (IOException e) {
      System.err.println("Error de input/output: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  private static int countLines() {
    try (BufferedReader br = new BufferedReader(new FileReader(CALIFICACIONES.toFile(), StandardCharsets.UTF_8))) {
      String lines;
      int n = 0;

      while ((lines = br.readLine()) != null) {
        n++;
      }

      return n;
    } catch (IOException e) {
      System.err.println("Error de input/output: " + e.getMessage());
      return 0;
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      return 0;
    }
  }

  private static void addCalification() {
    createCalification();
    try (
        BufferedWriter bw = new BufferedWriter(new FileWriter(CALIFICACIONES.toFile(), StandardCharsets.UTF_8, true))) {
      boolean flag = false;
      String name = "", module = "";
      double note = 0;
      int id = countLines();
      do {
        System.out.printf("Introduce el nombre del alumno: ");
        name = BR.readLine();
        if (name.isBlank()) {
          System.err.printf("Introduce un nombre correcto%n%n");
          continue;
        }
        System.out.printf("Introduce el nombre del módulo: ");
        module = BR.readLine();
        if (module.isBlank()) {
          System.err.printf("Introduce un módulo correcto%n%n");
          continue;
        }
        System.out.printf("Introduce la nota de %s: ", name);
        String raw = BR.readLine().trim().replace(',', '.');
        if (raw.isEmpty()) {
          System.err.printf("La nota no puede estar vacía%n%n");
          continue;
        }
        try {
          note = Double.parseDouble(raw);
        } catch (NumberFormatException e) {
          System.err.printf("Introduce un número válido%n%n");
          continue;
        }
        if (Double.isNaN(note) || note < 0 || note > 10) {
          System.err.printf("Introduce una nota entre 0 y 10%n%n");
          continue;
        }

        flag = true;
      } while (flag == false);

      bw.newLine();
      bw.write(id + ";" + name + ";" + module + ";" + note);
    } catch (IOException e) {
      System.err.println("Error de input/output: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  private static void showAllNotes() {
    try (BufferedReader br = new BufferedReader(new FileReader(CALIFICACIONES.toFile(), StandardCharsets.UTF_8))) {
      String line;
      int calification = 0;

      br.readLine();
      while ((line = br.readLine()) != null) {
        if (line.isBlank())
          continue;
        String[] fields = line.split(";");
        double note = Double.parseDouble(fields[NOTE]);
        calification++;

        System.out.printf("%s tiene un %.2f en %s%n", fields[NAME], note, fields[MODULE]);
      }

      if (calification == 0) {
        System.out.println("No existen calificaciones");
        return;
      }
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  private static void showAllApprovedNotes() {
    try (BufferedReader br = new BufferedReader(new FileReader(CALIFICACIONES.toFile(), StandardCharsets.UTF_8))) {
      String line;
      int calification = 0;

      br.readLine();
      while ((line = br.readLine()) != null) {
        if (line.isBlank())
          continue;

        String[] fields = line.split(";");
        double note = Double.parseDouble(fields[NOTE]);

        if (note < 5)
          continue;

        System.out.printf("%s tiene un %.2f en %s%n", fields[NAME], note, fields[MODULE]);
        calification++;
      }

      if (calification == 0) {
        System.out.println("No existen calificaciones");
        return;
      }
    } catch (IOException e) {
      System.err.println("Error de entrada/salida: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  private static void average() {
    try (BufferedReader br = new BufferedReader(new FileReader(CALIFICACIONES.toFile(), StandardCharsets.UTF_8))) {
      String line;
      float total = 0;
      int calification = 0;

      br.readLine();
      while ((line = br.readLine()) != null) {
        if (line.isBlank())
          continue;

        String[] fields = line.split(";");
        double note = Double.parseDouble(fields[NOTE]);

        total += note;
        calification++;
      }

      if (calification == 0) {
        System.out.println("No existen calificaciones");
        return;
      }

      System.out.printf("La nota media es de %.2f%n", total / calification);
    } catch (IOException e) {
      System.err.println("Error de entrada/salida: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  private static void findByName() {
    try (BufferedReader br = new BufferedReader(new FileReader(CALIFICACIONES.toFile(), StandardCharsets.UTF_8))) {
      System.out.printf("Introduce el nombre del usuario del que quieres acceder a toda su información: ");
      String name = BR.readLine();
      int calification = 0;

      String linea;

      br.readLine();
      while ((linea = br.readLine()) != null) {
        if (linea.isBlank())
          continue;

        String[] fields = linea.split(";");

        calification++;

        if (fields[NAME].equalsIgnoreCase(name.trim())) {
          System.out.printf("%s tiene un %.2f en %s%n", fields[NAME], Double.parseDouble(fields[NOTE]), fields[MODULE]);
          return;
        }
      }

      if (calification == 0) {
        System.out.println("No existen calificaciones");
        return;
      }

      System.out.println("No existe " + name);

    } catch (IOException e) {
      System.err.println("Error de entrada/salida: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }

  }

  private static void options(int o) {
    switch (o) {
      case 0:
        System.out.println("Saliendo del proyecto...");
        break;
      case 1:
        createCalification();
        break;
      case 2:
        addCalification();
        break;
      case 3:
        showAllNotes();
        break;
      case 4:
        showAllApprovedNotes();
        break;
      case 5:
        average();
        break;
      case 6:
        findByName();
        break;
      default:
        System.out.println("Opción no válida");
        break;
    }
  }

  private static void input() {
    int option = -1;
    do {
      System.out.printf("%n\t--¿Qué deseas hacer?--%n");
      System.out.printf("1. Crear calificaciones.csv con cavecera%n");
      System.out.printf("2. Añadir calificicón%n");
      System.out.printf("3. Mostrar calificaciones%n");
      System.out.printf("4. Mostrar calificaciones aprobadas%n");
      System.out.printf("5. Calcular nota media%n");
      System.out.printf("6. Buscar por nombre dol alumno%n");
      System.out.printf("0. Salir%n");
      System.out.printf("Opción: ");
      try {
        option = Integer.parseInt(BR.readLine().trim());
        options(option);
      } catch (NumberFormatException e) {
        System.err.println("Introduce un número");
        option = -1;
      } catch (IOException e) {
        System.err.println("Error: " + e);
        option = 0;
      }
    } while (option != 0);
  }

  public static void main(String[] args) throws Exception {
    input();
    BR.close();
  }
}
