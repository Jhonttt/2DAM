package ejercicio5;

import java.util.ArrayList;
import java.util.List;
import utils.Colors;

public class PruebaJoin {
  private static final List<String> colors = new ArrayList<>(List.of(
      Colors.BLUE,
      Colors.PURPLE,
      Colors.YELLOW,
      Colors.RED,
      Colors.CYAN));

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 5; i++) {
      HiloJoin h = new HiloJoin((i + 1), colors.get(i));
      h.start();
      h.join();
    }
    System.out.println(Colors.WHITE + "Todos los hilos creados");
  }
}