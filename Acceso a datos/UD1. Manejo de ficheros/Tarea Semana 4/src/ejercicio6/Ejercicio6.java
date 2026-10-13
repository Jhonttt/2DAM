package ejercicio6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Ejercicio6 {
  private static final String RESET = "\u001B[0m";
  private static final String RED = "\u001B[31m";
  private static final String GREEN = "\u001B[32m";
  private static final Path FICHERO = Path.of("empleados.dat");

  private static void readData() {
    System.out.println("\nLeyendo los datos del fichero...\n------------------------------");

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO.toFile()))) {
      double total, max, previousMax;
      String maxEmpleado = "";
      total = max = previousMax = 0.0;

      List<?> unknownList = (List<?>) ois.readObject();
      List<Empleado> empleados = new ArrayList<>();

      for (Object obj : unknownList) {
        if (obj instanceof Empleado empleado) {
          empleados.add(empleado);
        }
      }

      for (Empleado e : empleados) {
        System.out.println(e);
        total += e.getSalario();
        max = Math.max(max, e.getSalario());
        if (max > previousMax) {
          maxEmpleado = e.getNombre();
          previousMax = max;
        }
      }

      System.out.println("Hay un total de " + empleados.size() + " empleados");
      System.out.printf(Locale.ROOT, "El salario medio es de %.2f euros%n", total / empleados.size());
      System.out.printf("La persona con el mayor sueldo es %s%n", maxEmpleado);
      System.out.println(GREEN + "------------------------------\nLectura finalizada." + RESET);

    } catch (IOException | ClassNotFoundException e) {
      System.out.println(RED + "Error al leer el archivo: " + e.getMessage() + RESET);
    }
  }

  private static void storeData(List<Empleado> empleados) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO.toFile()))) {
      oos.writeObject(empleados);

      System.out.println(GREEN + "Empleados guardadas correctamente." + RESET);
    } catch (IOException e) {
      System.out.println(RED + "Error de E/S al escribir: " + e.getMessage() + RESET);
    }
  }

  public static void main(String[] args) {
    Empleado e1 = new Empleado(1, "Ana López", "Recursos Humanos", 2500.50);
    Empleado e2 = new Empleado(2, "Carlos Pérez", "Ventas", 1800.00);
    Empleado e3 = new Empleado(3, "Marta Sánchez", "IT", 3200.75);
    Empleado e4 = new Empleado(4, "Luis Gómez", "Marketing", 2100.00);
    Empleado e5 = new Empleado(5, "Elena Navarro", "Dirección", 4500.00);

    List<Empleado> empleados = new ArrayList<>(List.of(e1, e2, e3, e4, e5));
    storeData(empleados);
    readData();
  }
}
