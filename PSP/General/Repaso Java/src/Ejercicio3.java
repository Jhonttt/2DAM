import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio3 {
  private final static int ARR_SIZE = 10;
  private final static int[] NUMS = new int[ARR_SIZE];
  private final static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
  private static int size = 0;

  public static void sort(int[] arr, int size, int value) {
    int pos = size;
    for (int i = 0; i < size; i++) {
      if (value < arr[i]) {
        pos = i;
        break;
      }
    }

    for (int i = size; i > pos; i--) {
      arr[i] = arr[i - 1];
    }

    arr[pos] = value;
  }

  public static void askForNumbers(int numToAsk) {
    try {
      for (int i = 0; i < numToAsk; i++) {
        System.out.printf("Número %d: ", size + 1);
        int n = Integer.parseInt(BR.readLine());

        if (size == 0) {
          NUMS[0] = n;
        } else {
          sort(NUMS, size, n);
        }

        size++;
      }
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  public static void main(String[] args) throws IOException {
    askForNumbers(5);
    askForNumbers(5);

    System.out.printf("\n--Array de %d números insertado-- \n", size);
    for (int i = 0; i < size; i++) {
      System.out.printf("%d%s", NUMS[i], i < ARR_SIZE - 1 ? ", " : ".");
    }

    BR.close();
  }
}