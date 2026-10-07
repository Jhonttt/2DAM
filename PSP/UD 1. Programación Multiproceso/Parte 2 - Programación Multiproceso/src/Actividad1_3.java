import java.util.Locale;

public class Actividad1_3 {

  private static final int ARG1 = 0;
  private static final int ARG2 = 1;


  /** 
   * @param num
   * @return String
   */
  private static String normalize(String num) {
    return num.replace(',', '.');
  }

  /** 
   * @param num
   * @return boolean
   */
  private static boolean isNumber(String num) {
    try {
      Double.parseDouble(normalize(num));
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /** 
   * @param args
   */
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Introduce 2 números por argumento");
      System.exit(2);
    }

    if (!isNumber(args[ARG1]) || !isNumber(args[ARG2])) {
      System.out.println("Ambos argumentos deben ser números");
      System.exit(2);
    }

    double n1 = Double.parseDouble(normalize(args[ARG1]));
    double n2 = Double.parseDouble(normalize(args[ARG2]));

    System.out.printf(Locale.ROOT, "%.2f + %.2f = %.2f", n1, n2, n1 + n2);
    System.exit(0);
  }
}
