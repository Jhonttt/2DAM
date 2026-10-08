import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio10 {
  private static final String CSV_NAME = "alumnos.csv";
  private static final int GRADE_COLUMN = 2;

  public static void main(String[] args) {
    try (BufferedReader br = Files.newBufferedReader(Path.of(CSV_NAME), StandardCharsets.UTF_8)) {
      String line;
      float total = 0;
      int students = 0;

      br.readLine();
      while ((line = br.readLine()) != null) {
        if (line.isBlank()) continue;
        String[] fields = line.split(";");
        total += Integer.parseInt(fields[GRADE_COLUMN]);
        students++;
      }

      if (students == 0) {
        System.out.println("No existen alumnos");
        return ;
      }

      System.out.printf("La media es de los %d es de %.2f", students, total / students);
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
