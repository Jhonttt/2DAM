import java.io.File;
import java.io.IOException;

public class Ejercicio2 {
  public static void main(String[] args) {
    File f = new File("datos.txt");

    try {
      if (!f.exists()) f.createNewFile();
    } catch (IOException e) {
      System.err.println("Error al crear el archivo: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }

    System.out.printf("Nombre: %s\n", f.getName());
    System.out.printf("Ruta absoluta: %s\n", f.getAbsolutePath());
    System.out.printf("¿Existe?: %s\n", f.exists() ? "Si" : "No");
    System.out.printf("¿Fichero?: %s\n", f.isFile() ? "Si" : "No");
    System.out.printf("Tamaño en bytes: %s\n", f.length());
  }
}
