import java.io.BufferedWriter;
import java.io.FileWriter;

public class Ejercicio7 {
  public static final String CSV_NAME = "alumnos.csv";

  public static void main(String[] args) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_NAME))) {
      bw.write("id;nombre;edad;ciclo");
      bw.newLine();

      bw.write("1;Ana;20;DAM");
      bw.newLine();

      bw.write("2;Pedro;21;DAW");
      bw.newLine();

      bw.write("3;Laura;19;ASIR");
      bw.newLine();

      bw.write("4;Juan;22;DAW");
      bw.newLine();

      bw.write("5;Marta;20;DAM");
      bw.newLine();
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
