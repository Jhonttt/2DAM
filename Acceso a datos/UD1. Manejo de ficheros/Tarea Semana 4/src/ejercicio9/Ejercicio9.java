package ejercicio9;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

public class Ejercicio9 {

  private static final String RESET = "\u001B[0m";
  private static final String RED = "\u001B[31m";
  private static final String GREEN = "\u001B[32m";
  private static final Path FICHERO = Path.of("cursos.dat");

  private static void readData() {
    System.out.println(GREEN + "\nLeyendo los datos del fichero..." + RESET + "\n------------------------------");

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO.toFile()))) {

      Curso[] cursos = (Curso[]) ois.readObject();

      for (Curso c : cursos) {
        System.out.printf("Nombre: %s%nDuración: %d horas%nProfesor: %s%nCorreo: %s%n-----------------------------%n",
            c.getNombre(), c.getHoras(), c.getProfesor().getNombre(), c.getProfesor().getEmail());
      }

      System.out.println(GREEN + "Lectura finalizada." + RESET);

    } catch (IOException | ClassNotFoundException e) {
      System.out.println(RED + "Error al leer el archivo: " + e.getMessage() + RESET);
    }
  }

  private static void storeData(Curso... cursos) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO.toFile()))) {
      oos.writeObject(cursos);

      System.out.println(GREEN + "Cursos guardadas correctamente." + RESET);
    } catch (IOException e) {
      System.out.println(RED + "Error de E/S al escribir: " + e.getMessage() + RESET);
    }
  }

  public static void main(String[] args) {
    Profesor prof1 = new Profesor("ada@instituto.es", "Ada Lovelace");
    Profesor prof2 = new Profesor("alan@instituto.es", "Alan Turing");

    Curso curso1 = new Curso("Acceso a Datos", 120, prof1);
    Curso curso2 = new Curso("Desarrollo de Interfaces", 100, prof2);
    Curso curso3 = new Curso("Programación Multimedia", 80, prof1);

    storeData(curso1, curso2, curso3);
    readData();
  }
}
