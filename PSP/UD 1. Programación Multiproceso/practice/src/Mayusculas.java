
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mayusculas {

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String l;
      while ((l = br.readLine()) != null) {
        System.out.println(l.toUpperCase());
        System.out.flush();
      }
    }
  }

}