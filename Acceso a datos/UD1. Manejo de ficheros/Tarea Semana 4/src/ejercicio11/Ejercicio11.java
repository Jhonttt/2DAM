package ejercicio11;

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
import java.util.List;

public class Ejercicio11 {
  private static final String RESET = "\u001B[0m";
  private static final String RED = "\u001B[31m";
  private static final String GREEN = "\u001B[92m";
  private static final Path FICHERO = Path.of("clientes_backup.dat");
  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
  private static List<Cliente> clientes = new ArrayList<>();

  private static void loadClients() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO.toFile()))) {
      List<Cliente> loadedClients = new ArrayList<>();

      for (Object obj : (List<?>) ois.readObject()) {
        if (obj instanceof Cliente cliente) {
          loadedClients.add(cliente);
        }
      }

      clientes = loadedClients;
      System.out.println(GREEN + "\nClientes cargados correctamente" + RESET);
    } catch (FileNotFoundException e) {
      System.out.println(RED + "\nNo se ha encontrado el archivo: " + FICHERO + RESET);
    } catch (IOException | ClassNotFoundException e) {
      System.out.println(RED + "\nError al leer el archivo: " + e.getMessage() + RESET);
    }
  }

  private static void saveClients() {
    if (clientes.isEmpty()) {
      System.out.println(RED + "\nNo hay clientes para guardar" + RESET);
      return;
    }

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO.toFile()))) {
      oos.writeObject(clientes);
      System.out.println(GREEN + "\nClientes guardados correctamente" + RESET);
    } catch (IOException e) {
      System.out.println(RED + "Error de E/S al escribir: " + e.getMessage() + RESET);
    }
  }

  private static void deleteClient() {
    while (true) {
      try {
        if (clientes.isEmpty()) {
          System.out.println(RED + "\nNo hay clientes para eliminar" + RESET);
          return;
        }

        System.out.print("\nIntroduce el código a eliminar: ");
        int searchCode = Integer.parseInt(BR.readLine());
        if (searchCode <= 0) {
          System.err.println(RED + "Introduce un código a buscar válido (> 0)\n" + RESET);
          continue;
        }

        Cliente found = clientes.stream().filter(c -> c.getCodigo() == searchCode).findFirst().orElse(null);

        if (found == null) {
          System.out.println(RED + "No se ha encontrado ningún cliente con el código " + searchCode + RESET);
          return;
        }

        clientes.remove(found);
        System.out
            .println(GREEN + "Cliente " + found.getNombre() + " con el código " + searchCode + " eliminado" + RESET);
        return;
      } catch (IOException | NumberFormatException e) {
        System.err.println(RED + "Error: Formato numérico incorrecto. Por favor, inténtalo de nuevo.\n" + RESET);
      }
    }
  }

  private static void searchClient() {
    while (true) {
      try {
        if (clientes.isEmpty()) {
          System.out.println(RED + "\nNo hay clientes para buscar" + RESET);
          return;
        }

        System.out.print("\nIntroduce el código a buscar: ");
        int searchCode = Integer.parseInt(BR.readLine());
        if (searchCode <= 0) {
          System.err.println(RED + "Introduce un código a buscar válido (> 0)\n" + RESET);
          continue;
        }

        Cliente found = clientes.stream().filter(c -> c.getCodigo() == searchCode).findFirst().orElse(null);

        if (found == null) {
          System.out.println(RED + "No se ha encontrado ningún cliente con el código " + searchCode + RESET);
          return;
        }

        System.out.println(found);

        return;
      } catch (IOException | NumberFormatException e) {
        System.err.println(RED + "Error: Formato numérico incorrecto. Por favor, inténtalo de nuevo.\n" + RESET);
      }
    }
  }

  private static void listClients() {
    if (clientes.isEmpty()) {
      System.out.println(RED + "\nNo hay clientes para listar" + RESET);
      return;
    }
    System.out.println(GREEN + "\nListando clientes..." + RESET);
    for (Cliente c : clientes) {
      System.out.println(c);
    }
  }

  private static void addClient() {
    while (true) {
      try {
        System.out.print("\nCódigo: ");
        int code = Integer.parseInt(BR.readLine());
        if (code <= 0) {
          System.err.println(RED + "Introduce un código válido (> 0)\n" + RESET);
          continue;
        }

        if (clientes.stream().anyMatch(c -> c.getCodigo() == code)) {
          System.err.println(RED + "Introduce un código único, ese código ya existe\n" + RESET);
          continue;
        }

        System.out.print("Nombre: ");
        String name = BR.readLine();
        if (name.isBlank()) {
          System.err.println(RED + "El nombre no puede estar vacío\n" + RESET);
          continue;
        }

        System.out.print("Télefono: ");
        String telefono = BR.readLine();
        if (telefono.isBlank()) {
          System.err.println(RED + "El télefono no puede estar vacío\n" + RESET);
          continue;
        }

        System.out.print("Email: ");
        String email = BR.readLine();
        if (email.isBlank()) {
          System.err.println(RED + "El email no puede estar vacío\n" + RESET);
          continue;
        }

        System.out.print("Saldo pendiente: ");
        double saldoPendiente = Double.parseDouble(BR.readLine());
        if (saldoPendiente < 0) {
          System.err.println(RED + "El saldo pendiente no puede ser negativo\n" + RESET);
          continue;
        }

        Cliente c = new Cliente(code, name, telefono, email, saldoPendiente);
        clientes.add(c);
        break;
      } catch (IOException | NumberFormatException e) {
        System.err.println(RED + "Error: Formato numérico incorrecto. Por favor, inténtalo de nuevo.\n" + RESET);
      }
    }
  }

  private static void options(int o) {
    switch (o) {
      case 0 -> System.out.println(RED + "\nSaliendo del proyecto..." + RESET);
      case 1 -> addClient();
      case 2 -> listClients();
      case 3 -> searchClient();
      case 4 -> deleteClient();
      case 5 -> saveClients();
      case 6 -> loadClients();
      default -> System.out.println(RED + "\nOpción no válida" + RESET);
    }
  }

  private static void input() {
    int option;
    do {
      System.out.printf("%n%s\t--¿Qué deseas hacer?--%s%n", GREEN, RESET);
      System.out.printf("1. Añadir cliente%n");
      System.out.printf("2. Mostrar clientes%n");
      System.out.printf("3. Buscar cliente%n");
      System.out.printf("4. Eliminar cliente%n");
      System.out.printf("5. Guardar copia de seguridad%n");
      System.out.printf("6. Recuperar copia de seguridad%n");
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
