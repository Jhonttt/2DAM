package ejercicio12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ejercicio12 {
  private static final String RESET = "\u001B[0m";
  private static final String RED = "\u001B[31m";
  private static final String GREEN = "\u001B[92m";
  private static final Path FICHERO = Path.of("alumnos_serializados.dat");
  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
  private static List<Alumno> alumnos = new ArrayList<>();

  private static void loadClients() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO.toFile()))) {
      List<Alumno> loadedStudents = new ArrayList<>();

      for (Object obj : (List<?>) ois.readObject()) {
        if (obj instanceof Alumno alumno) {
          loadedStudents.add(alumno);
        }
      }

      alumnos = loadedStudents;
      System.out.println(GREEN + "\nAlumnos cargados correctamente" + RESET);
    } catch (FileNotFoundException e) {
      System.out.println(RED + "\nNo se ha encontrado el archivo: " + FICHERO + RESET);
    } catch (IOException | ClassNotFoundException e) {
      System.out.println(RED + "\nError al leer el archivo: " + e.getMessage() + RESET);
    }
  }

  private static void saveClients() {
    if (alumnos.isEmpty()) {
      System.out.println(RED + "\nNo hay alumnos para guardar" + RESET);
      return;
    }

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO.toFile()))) {
      oos.writeObject(alumnos);
      System.out.println(GREEN + "\nAlumnos guardados correctamente" + RESET);
    } catch (IOException e) {
      System.out.println(RED + "Error de E/S al escribir: " + e.getMessage() + RESET);
    }
  }

  private static void deleteStudent() {
    while (true) {
      try {
        if (alumnos.isEmpty()) {
          System.out.println(RED + "\nNo hay alumnos para eliminar" + RESET);
          return;
        }

        System.out.print("\nIntroduce el número de expediente a eliminar: ");
        int searchCode = Integer.parseInt(BR.readLine());
        if (searchCode <= 0) {
          System.err.println(RED + "Introduce un código a buscar válido (> 0)\n" + RESET);
          continue;
        }

        Alumno found = alumnos.stream().filter(a -> a.getNumeroExpediente() == searchCode).findFirst().orElse(null);

        if (found == null) {
          System.out
              .println(RED + "No se ha encontrado ningún alumno con el número de expediente " + searchCode + RESET);
          return;
        }

        alumnos.remove(found);
        System.out
            .println(GREEN + "Alumno " + found.getNombre() + " con el número de expediente " + searchCode + " eliminado"
                + RESET);
        return;
      } catch (IOException | NumberFormatException e) {
        System.err.println(RED + "Error: Formato numérico incorrecto. Por favor, inténtalo de nuevo.\n" + RESET);
      }
    }
  }

  private static void listMaxStudent() {
    if (alumnos.isEmpty()) {
      System.out.println(RED + "\nNo hay alumnos para listar" + RESET);
      return;
    }

    Alumno maxStudent = alumnos.stream().max(Comparator.comparingDouble(Alumno::getNotaMedia)).orElse(null);
    System.out.println("\nLa persona con la nota media más alta es: \n" + maxStudent);
  }

  private static void listPassedStudents() {
    if (alumnos.isEmpty()) {
      System.out.println(RED + "\nNo hay alumnos para listar" + RESET);
      return;
    }

    List<Alumno> aprobados = alumnos.stream().filter(a -> a.getNotaMedia() >= 5).toList();

    if (aprobados.isEmpty()) {
      System.out.println(RED + "\nNo hay alumnos aprobados" + RESET);
      return;
    }

    System.out.println(GREEN + "\nListando alumnos aprobados..." + RESET);
    aprobados.forEach(System.out::println);
  }

  private static void searchStudent() {
    while (true) {
      try {
        if (alumnos.isEmpty()) {
          System.out.println(RED + "\nNo hay alumnos para buscar" + RESET);
          return;
        }

        System.out.print("\nIntroduce el número del expediente a buscar: ");
        int searchCode = Integer.parseInt(BR.readLine());
        if (searchCode <= 0) {
          System.err.println(RED + "Introduce un código a buscar válido (> 0)\n" + RESET);
          continue;
        }

        Alumno found = alumnos.stream().filter(a -> a.getNumeroExpediente() == searchCode).findFirst().orElse(null);

        if (found == null) {
          System.out
              .println(RED + "No se ha encontrado ningún alumno con el número de expediente " + searchCode + RESET);
          return;
        }

        System.out.println(found);
        return;
      } catch (IOException | NumberFormatException e) {
        System.err.println(RED + "Error: Formato numérico incorrecto. Por favor, inténtalo de nuevo.\n" + RESET);
      }
    }
  }

  private static void listStudents() {
    if (alumnos.isEmpty()) {
      System.out.println(RED + "\nNo hay alumnos para listar" + RESET);
      return;
    }
    System.out.println(GREEN + "\nListando alumnos..." + RESET);
    for (Alumno a : alumnos) {
      System.out.println(a);
    }
  }

  private static void addStudent() {
    while (true) {
      try {
        System.out.print("\nNúmero de expediente: ");
        int nExp = Integer.parseInt(BR.readLine());
        if (nExp <= 0) {
          System.err.println(RED + "Introduce un código válido (> 0)\n" + RESET);
          continue;
        }

        if (alumnos.stream().anyMatch(c -> c.getNumeroExpediente() == nExp)) {
          System.err.println(RED + "Introduce un número de expediente único, ese ya existe\n" + RESET);
          continue;
        }

        System.out.print("Nombre: ");
        String name = BR.readLine();
        if (name.isBlank()) {
          System.err.println(RED + "El nombre no puede estar vacío\n" + RESET);
          continue;
        }

        System.out.print("Apellidos: ");
        String lastName = BR.readLine();
        if (lastName.isBlank()) {
          System.err.println(RED + "Los apellidos no pueden estar vacío\n" + RESET);
          continue;
        }

        System.out.print("Ciclo: ");
        String cicle = BR.readLine();
        if (cicle.isBlank()) {
          System.err.println(RED + "El ciclo no puede estar vacío\n" + RESET);
          continue;
        }

        System.out.print("Nota media: ");
        double avgNote = Double.parseDouble(BR.readLine());
        if (avgNote < 0 || avgNote > 10) {
          System.err.println(RED + "La nota media tiene que estar en el rango (0-10)\n" + RESET);
          continue;
        }

        Alumno a = new Alumno(lastName, cicle, cicle, avgNote, nExp);
        alumnos.add(a);
        break;
      } catch (IOException | NumberFormatException e) {
        System.err.println(RED + "Error: Formato numérico incorrecto. Por favor, inténtalo de nuevo.\n" + RESET);
      }
    }
  }

  private static void options(int o) {
    switch (o) {
      case 0 -> System.out.println(RED + "\nSaliendo del proyecto..." + RESET);
      case 1 -> addStudent();
      case 2 -> listStudents();
      case 3 -> searchStudent();
      case 4 -> listPassedStudents();
      case 5 -> listMaxStudent();
      case 6 -> deleteStudent();
      case 7 -> saveClients();
      case 8 -> loadClients();
      default -> System.out.println(RED + "\nOpción no válida" + RESET);
    }
  }

  private static void input() {
    int option;
    do {
      System.out.printf("%n%s\t--¿Qué deseas hacer?--%s%n", GREEN, RESET);
      System.out.printf("1. Añadir alumno%n");
      System.out.printf("2. Mostrar todos los alumnos%n");
      System.out.printf("3. Buscar alumno por expediente%n");
      System.out.printf("4. Mostrar alumnos aprobados%n");
      System.out.printf("5. Mostrar alumno con mayor nota media%n");
      System.out.printf("6. Eliminar alumno%n");
      System.out.printf("7. Guardar datos%n");
      System.out.printf("8. Cargar datos%n");
      System.out.printf("%s0. Salir%s%n", RED, RESET);
      System.out.printf("Opción: ");
      try {
        option = Integer.parseInt(BR.readLine().trim());
        options(option);
      } catch (IOException | NumberFormatException e) {
        System.err.println(RED + "\nIntroduce un número correcto" + RESET);
        option = -1;
      }
    } while (option != 0);
  }

  public static void main(String[] args) {
    input();
  }
}
