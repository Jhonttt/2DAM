package ejercicio1;

import utils.Colors;
import utils.Funciones;


public class DentroFuera {
  public static void main(String[] args) throws Exception {
    new HiloFor().start();
    Funciones.printMsg(10, Colors.RED, "Estoy fuera del hilo");
    // for (int i = 0; i < 1000; i++) {
    //   System.out.println(Colors.RED + "Estoy fuera del bucle");
    // }
  }
}
