
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class Ejercicio2 {
  private static final String RESET = "\u001B[0m";
  private static final String RED = "\u001B[31m";
  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
  private static final int STUDENTS = 5;
  private static final Path FICHERO = Path.of("alumnos.dat");

  private static void readData() {
    try (DataInputStream dis = new DataInputStream(new FileInputStream(FICHERO.toFile()))) {
      while (dis.available() > 0) {
        int exp = dis.readInt();
        String name = dis.readUTF();
        int age = dis.readInt();
        double note = dis.readDouble();
        System.out.printf("Expediente: %d%nNombre: %s%nEdad: %d%nNota media: %.2f\n---------------------\n", exp, name,
            age, note);
      }

    } catch (IOException e) {
      System.err.println(RED + "Error de entrada/salida: " + e.getMessage() + RESET);
    }
  }

  private static void storeData(int exp, String name, int age, double note) {
    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FICHERO.toFile(), true))) {
      dos.writeInt(exp);
      dos.writeUTF(name);
      dos.writeInt(age);
      dos.writeDouble(note);
    } catch (IOException e) {
      System.err.println(RED + "Error de entrada/salida: " + e.getMessage() + RESET);
    }
  }

  private static void askStudents() {
    while (true) {
      try {
        System.out.print("Número de expediente: ");
        int exp = Integer.parseInt(BR.readLine());
        if (exp <= 0) {
          System.err.println(RED + "Introduce un expediente válido mayor que 0\n" + RESET);
          continue;
        }

        System.out.print("Nombre: ");
        String name = BR.readLine();
        if (name.isBlank()) {
          System.err.println(RED + "El nombre no puede estar vacío\n" + RESET);
          continue;
        }

        System.out.print("Edad: ");
        int age = Integer.parseInt(BR.readLine());
        if (age < 12 || age > 60) {
          System.err.println(RED + "Introduce una edad válida (12-60)\n" + RESET);
          continue;
        }

        System.out.print("Nota media: ");
        double note = Double.parseDouble(BR.readLine());
        if (Double.isNaN(note) || note < 0 || note > 10) {
          System.err.printf("%sIntroduce una nota entre 0 y 10%n%n %s", RED, RESET);
          continue;
        }

        storeData(exp, name, age, note);
        break;
      } catch (IOException | NumberFormatException e) {
        System.err.println(RED + "Error: Formato numérico incorrecto. Por favor, inténtalo de nuevo.\n" + RESET);
      }
    }
  }

  public static void main(String[] args) {
    FICHERO.toFile().delete();
    
    for (int i = 0; i < STUDENTS; i++) {
      System.out.println("Estudiante " + (i + 1) + ": ");
      askStudents();
      System.out.println("---------------------");
    }

    System.out.println("\nLectura de " + FICHERO.toFile());
    readData();
  }
}
