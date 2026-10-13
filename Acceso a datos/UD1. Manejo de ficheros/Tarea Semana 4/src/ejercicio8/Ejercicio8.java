package ejercicio8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio8 {
  private static final String RESET = "\u001B[0m";
  private static final String RED = "\u001B[31m";
  private static final String GREEN = "\u001B[32m";
  private static final String BLUE = "\u001B[34m";
  private static final Path FICHERO = Path.of("vehiculos.dat");

  private static void readData() {
    System.out.println(GREEN + "\nLeyendo los datos del fichero...\n------------------------------" + RESET);

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO.toFile()))) {
      double total = 0;

      List<?> unknownList = (List<?>) ois.readObject();
      List<Vehiculo> vehiculos = new ArrayList<>();

      for (Object obj : unknownList) {
        if (obj instanceof Vehiculo vehiculo) {
          vehiculos.add(vehiculo);
        }
      }

      Vehiculo repMasCara = vehiculos.get(0);

      System.out.println(BLUE + "Todos los vehículos registrados: " + RESET);
      for (Vehiculo v : vehiculos) {
        System.out.println("  " + v);
        if (v.getCosteReparacion() > repMasCara.getCosteReparacion())
          repMasCara = v;
        total += v.getCosteReparacion();
      }

      System.out.println(BLUE + "Los vehículos con más de 100.000 kilometros: " + RESET);
      vehiculos.stream().filter(v -> v.getKilometros() > 100000)
          .forEach(v -> System.out.println("  " + v.getMatricula()));
      System.out
          .println(BLUE + "El vehiculo cuya reparación es la más cara: " + RESET + "\n  " + repMasCara.getMatricula());
      System.out.println(BLUE + "Importe total facturado en reparaciones: " + RESET + "\n  " + total + " euros");
      System.out.println(GREEN + "------------------------------\nLectura finalizada." + RESET);

    } catch (IOException | ClassNotFoundException e) {
      System.out.println(RED + "Error al leer el archivo: " + e.getMessage() + RESET);
    }
  }

  private static void storeData(List<Vehiculo> vehiculos) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO.toFile()))) {
      oos.writeObject(vehiculos);

      System.out.println(GREEN + "Empleados guardadas correctamente." + RESET);
    } catch (IOException e) {
      System.out.println(RED + "Error de E/S al escribir: " + e.getMessage() + RESET);
    }
  }

  public static void main(String[] args) {
    Vehiculo v1 = new Vehiculo("1234 LMN", "Toyota", "Corolla", 45000, 150.50);
    Vehiculo v2 = new Vehiculo("5678 XYZ", "Ford", "Focus", 120000, 500.00);
    Vehiculo v3 = new Vehiculo("9012 BCD", "Renault", "Clio", 15000, 0.0);
    Vehiculo v4 = new Vehiculo("3456 FGH", "Seat", "Ibiza", 85000, 320.75);
    Vehiculo v5 = new Vehiculo("7890 JKL", "Volkswagen", "Golf", 210000, 850.90);

    List<Vehiculo> vehiculos = new ArrayList<>(List.of(v1, v2, v3, v4, v5));
    storeData(vehiculos);
    readData();
  }
}
