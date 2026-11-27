package ejercicio2;

import utils.Colors;
import utils.Funciones;

public class HiloC extends Thread {
  private final int c;
  private final String color;

  public HiloC(int c, String color) {
    this.c = c;
    this.color = color;
    System.out.println(Colors.GREEN + "Creando hilo " + c);
  }

  @Override
  public void run() {
    Funciones.printMsgNumerado(5, color, "Hilo " + c + " línea");
  }
}
