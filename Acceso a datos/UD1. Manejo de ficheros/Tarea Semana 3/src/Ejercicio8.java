import java.io.BufferedReader;
import java.io.FileReader;

public class Ejercicio8 {
  private static final String CSV_NAME = "alumnos.csv";
  
  public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader(new FileReader(CSV_NAME))) {
      String line;

      br.readLine();
      while ((line = br.readLine()) != null) {
        String[] fields = line.split(";");


        System.out.printf("%s (%d años) - %s%n", fields[1], Integer.parseInt(fields[2]), fields[3]);
      }
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
