package ejercicio4;

import java.util.ArrayList;
import java.util.List;
import utils.Colors;

public class VariosHilosR {
  private static final List<String> colors = new ArrayList<>(List.of(
      Colors.BLUE,
      Colors.PURPLE,
      Colors.YELLOW,
      Colors.RED,
      Colors.CYAN));

  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      new HiloCR((i + 1), colors.get(i)).run();
    }
    System.out.println(Colors.WHITE + "Todos los hilos creados");
  }
}
