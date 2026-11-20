/**
 * Randoms
 */
public class Randoms {

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Necestio 2");
      System.exit(1);
    }

    int n1 = Integer.parseInt(args[0]);
    int n2 = Integer.parseInt(args[1]);

    System.out.println(n1 + n2);
  }
}