package ejercicio2;

import java.util.ArrayList;
import java.util.List;
import utils.Colors;

public class VariosHilos {
  private static final List<String> colors = new ArrayList<>(List.of(
      Colors.BLUE,
      Colors.PURPLE,
      Colors.YELLOW,
      Colors.RED,
      Colors.CYAN));

  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      new HiloC((i + 1), colors.get(i)).start();
    }
    System.out.println(Colors.WHITE + "Todos los hilos creados");
  }
}
