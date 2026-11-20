
import java.util.ArrayList;
import java.util.List;
import utils.Colors;

public class TransformadorTexto {
  public static void main(String[] args) {
    if (args.length <= 0) {
      System.exit(1);
    }

    List<String> newArray = new ArrayList<>();
    for (String a : args) {
      String newWord = "";

      for (int i = a.length() - 1; i >= 0; i--) {
        char x = a.toUpperCase().charAt(i);
        newWord += Character.toString(x);
      }

      newArray.add(newWord);
    }

    String value = String.join(" - ", newArray);
    System.out.println(Colors.BLUE + value);
    System.exit(0);
  }
}
