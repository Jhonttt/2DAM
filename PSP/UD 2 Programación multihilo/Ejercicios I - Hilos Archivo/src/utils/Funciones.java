package utils;

public class Funciones {
  public static void printMsg(int it, String color, String msg) {
    for (int i = 0; i < it; i++) {
      System.out.println(color + msg);
    }
  }

  public static void printMsgNumerado(int it, String color, String msg) {
    for (int i = 1; i <= it; i++) {
      System.out.println(color + msg + " " + i + Colors.RESET);
    }
  }
}