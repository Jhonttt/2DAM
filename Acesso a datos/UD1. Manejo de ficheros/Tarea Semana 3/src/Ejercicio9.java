import java.io.BufferedReader;
import java.io.FileReader;

public class Ejercicio9 {
  private static final String CSV_NAME = "alumnos.csv";

  public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader(new FileReader(CSV_NAME))) {
      String line;

      br.readLine();
      while ((line = br.readLine()) != null) {
        String[] fields = line.split(";");

        if (fields[3].equals("DAM")) {
          System.out.println(fields[1]);
        }
      }
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
