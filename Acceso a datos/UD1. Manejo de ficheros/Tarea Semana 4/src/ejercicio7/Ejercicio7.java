package ejercicio7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio7 {
  private static final String RESET = "\u001B[0m";
  private static final String RED = "\u001B[31m";
  private static final String GREEN = "\u001B[92m";
  private static final Path FICHERO = Path.of("biblioteca.dat");
  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
  private static List<Libro> libros = new ArrayList<>();

  private static void loadBooks() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO.toFile()))) {
      List<Libro> loadedBooks = new ArrayList<>();

      for (Object obj : (List<?>) ois.readObject()) {
        if (obj instanceof Libro libro) {
          loadedBooks.add(libro);
        }
      }

      libros = loadedBooks;
      System.out.println(GREEN + "\nLibros cargados correctamente" + RESET);
    } catch (IOException | ClassNotFoundException e) {
      System.out.println(RED + "\nError al leer el archivo: " + e.getMessage() + RESET);
    }
  }

  private static void saveBooks() {
    if (libros.isEmpty()) {
      System.out.println(RED + "\nNo hay libros para guardar" + RESET);
      return;
    }

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO.toFile()))) {
      oos.writeObject(libros);
      System.out.println(GREEN + "\nLibros guardados correctamente" + RESET);
    } catch (IOException e) {
      System.out.println(RED + "Error de E/S al escribir: " + e.getMessage() + RESET);
    }
  }

  private static void listBooks() {
    if (libros.isEmpty()) {
      System.out.println(RED + "\nNo hay libros registrados" + RESET);
      return;
    }
    System.out.println(GREEN + "\nListando libros..." + RESET);
    for (Libro l : libros) {
      System.out.println(l);
    }
  }

  private static void addBook() {
    while (true) {
      try {
        System.out.print("\nISBN: ");
        String isbn = BR.readLine();
        if (isbn.isBlank()) {
          System.err.println(RED + "El ISBN no puede estar vacío\n" + RESET);
          continue;
        }

        System.out.print("Titulo: ");
        String titulo = BR.readLine();
        if (titulo.isBlank()) {
          System.err.println(RED + "El titulo no puede estar vacío\n" + RESET);
          continue;
        }

        System.out.print("Autor: ");
        String autor = BR.readLine();
        if (autor.isBlank()) {
          System.err.println(RED + "El autor no puede estar vacío\n" + RESET);
          continue;
        }

        System.out.print("Año de publicación: ");
        int anioPublicacion = Integer.parseInt(BR.readLine());
        if (anioPublicacion < 1500 || anioPublicacion > 2026) {
          System.err.println(RED + "Introduce un año de publicación válido (1500-2026)\n" + RESET);
          continue;
        }

        System.out.print("¿Es prestado? (s/n): ");
        String prestado = BR.readLine();
        if (!"s".equalsIgnoreCase(prestado) && !"n".equalsIgnoreCase(prestado)) {
          System.err.printf("%sIntroduce un respuesta válida 's' o 'n'%n%s", RED, RESET);
          continue;
        }

        Libro l = new Libro(anioPublicacion, autor, isbn, prestado.equalsIgnoreCase("s"), titulo);
        libros.add(l);
        break;
      } catch (IOException | NumberFormatException e) {
        System.err.println(RED + "Error: Formato numérico incorrecto. Por favor, inténtalo de nuevo.\n" + RESET);
      }
    }
  }

  private static void options(int o) {
    switch (o) {
      case 0 -> System.out.println(RED + "\nSaliendo del proyecto..." + RESET);
      case 1 -> addBook();
      case 2 -> listBooks();
      case 3 -> saveBooks();
      case 4 -> loadBooks();
      default -> System.out.println(RED + "\nOpción no válida" + RESET);
    }
  }

  private static void input() {
    int option;
    do {
      System.out.printf("%n%s\t--¿Qué deseas hacer?--%s%n", GREEN, RESET);
      System.out.printf("1. Añadir libro%n");
      System.out.printf("2. Mostrar libros%n");
      System.out.printf("3. Guardar catálogo%n");
      System.out.printf("4. Cargar catálogo%n");
      System.out.printf("%s0. Salir%s%n", RED, RESET);
      System.out.printf("Opción: ");
      try {
        option = Integer.parseInt(BR.readLine().trim());
        options(option);
      } catch (IOException | NumberFormatException e) {
        System.err.println(RED + "Introduce un número correcto" + RESET);
        option = -1;
      }
    } while (option != 0);
  }

  public static void main(String[] args) {
    input();
  }
}
