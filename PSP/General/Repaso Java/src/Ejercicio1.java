import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("¿Cuántos números desea ingresar?: ");
            int nums = sc.nextInt();
            int[] numbers = new int[nums];

            for (int i = 0; i < nums; i++) {
                System.out.printf("Ingresa el número %d: ", i + 1);
                int num = sc.nextInt();
                numbers[i] = num;
            }

            for (int i = 0; i < numbers.length; i++) {
                System.out.printf("%d%s", numbers[i], i < numbers.length - 1 ? ", " : ".");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}