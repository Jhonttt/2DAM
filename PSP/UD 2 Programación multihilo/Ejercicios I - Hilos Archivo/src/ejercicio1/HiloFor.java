package ejercicio1;

import utils.Colors;
import utils.Funciones;

public class HiloFor extends Thread {
  @Override
  public void run() {
    Funciones.printMsg(10, Colors.GREEN, "Estoy dentro del hilo");
  }
}