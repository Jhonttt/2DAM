
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejercicio2 {
  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
  private static final int STUDENTS = 5;

  private static void askStudents() {
    try {
      System.out.println("Número de expediente: "); 
      System.out.println("Nombre: ");
      System.out.println("Edad: ");
      System.out.println("Nota media: ");
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  public static void main(String[] args) {

  }
}
