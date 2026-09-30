import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ejercicio11.Alumno;

public class Ejercicio12 {
  private static final String CSV_NAME = "salida_alumnos.csv";

  public static void main(String[] args) {
    List<Alumno> alumnos = new ArrayList<>();
    alumnos.add(new Alumno(1, "Juan", 20, "DAM"));
    alumnos.add(new Alumno(2, "Maria", 22, "DAW"));
    alumnos.add(new Alumno(3, "Pedro", 19, "ASIR"));
    alumnos.add(new Alumno(4, "Lucia", 21, "DAM"));
    alumnos.add(new Alumno(5, "Carlos", 23, "SMR"));

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_NAME))) {
      bw.write("id;nombre;edad;ciclo");
      for (Alumno a : alumnos) {
        bw.newLine();
        bw.write(a.getId() + ";" + a.getName() + ";" + a.getAge() + ";" + a.getCiclo());
      }
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }

  }

}
