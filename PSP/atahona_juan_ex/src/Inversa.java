
import java.util.ArrayList;
import java.util.List;
import utils.Colors;

public class Inversa {
  public static void main(String[] args) {
    if (args.length <= 0) {
      System.out.println(Colors.RED + "Introduce argumentos");
      System.exit(1);
    }

    List<String> newList = new ArrayList<>();
    for (String arg : args) {
      String reverse = new StringBuilder(arg.toUpperCase()).reverse().toString();
      newList.add(reverse);
    }

    String value = String.join(" - ", newList);
    System.out.println(Colors.BLUE + value);
    System.exit(0);
  }
}
