package ejercicio5;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

public class Ejercicio5 {

  private static final String RESET = "\u001B[0m";
  private static final String RED = "\u001B[31m";
  private static final String GREEN = "\u001B[32m";
  private static final Path FICHERO = Path.of("personas.dat");

  private static void readData() {
    System.out.println("\nLeyendo los datos del fichero...\n------------------------------");

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO.toFile()))) {

      while (true) {
        Persona p = (Persona) ois.readObject();
        System.out.println("Leido: " + p);
      }

    } catch (EOFException e) {
      System.out.println("------------------------------\n" + GREEN + "Lectura finalizada." + RESET);
    } catch (IOException | ClassNotFoundException e) {
      System.out.println(RED + "Error al leer el archivo: " + e.getMessage() + RESET);
    }
  }

  private static void storeData(Persona... personas) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO.toFile()))) {
      for (Persona p : personas) {
        oos.writeObject(p);
      }

      System.out.println(GREEN + "Personas guardadas correctamente." + RESET);

    } catch (IOException e) {
      System.out.println(RED + "Error de E/S al escribir: " + e.getMessage() + RESET);
    }
  }

  public static void main(String[] args) {
    Persona p1 = new Persona("Laura", "Martínez Gómez", 30, "71234567B");
    Persona p2 = new Persona("David", "Fernández Ruiz", 42, "45678901C");
    Persona p3 = new Persona("Carmen", "López Navarro", 19, "89012345D");

    storeData(p1, p2, p3);
    readData();
  }
}
