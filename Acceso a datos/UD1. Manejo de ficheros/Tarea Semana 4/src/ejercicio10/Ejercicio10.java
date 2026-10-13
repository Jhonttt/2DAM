package ejercicio10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

public class Ejercicio10 {

  private static final String RESET = "\u001B[0m";
  private static final String RED = "\u001B[31m";
  private static final String GREEN = "\u001B[32m";
  private static final Path FICHERO = Path.of("usuario.dat");

  private static void readData() {
    System.out.println(GREEN + "\nLeyendo los datos del fichero...\n------------------------------" + RESET);

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO.toFile()))) {

      Usuario usuario = (Usuario) ois.readObject();

      System.out.printf("Nombre: %s%nEmail: %s%nPassword: %s%n", usuario.getNombre(), usuario.getEmail(), usuario.getPassword());

      System.out.println(GREEN + "------------------------------\nLectura finalizada." + RESET);

    } catch (IOException | ClassNotFoundException e) {
      System.out.println(RED + "Error al leer el archivo: " + e.getMessage() + RESET);
    }
  }

  private static void storeData(Usuario user) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO.toFile()))) {
      oos.writeObject(user);

      System.out.println(GREEN + "Usuario guardado correctamente." + RESET);
    } catch (IOException e) {
      System.out.println(RED + "Error de E/S al escribir: " + e.getMessage() + RESET);
    }
  }

  public static void main(String[] args) {
    Usuario user = new Usuario("Juan Atahona", "juan@instituto.es", "123456");
    storeData(user);
    readData();
  }
}
