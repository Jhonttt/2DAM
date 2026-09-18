import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int[] tabla1 = new int[5];
            int[] tabla2 = new int[5];
            int[] tabla3 = new int[10];

            System.out.printf("\tINGRESA %d NÚMEROS PARA LA PRIMERA TABLA: \n", tabla1.length);
            for (int i = 0; i < tabla1.length; i++) {
                System.out.printf("Número %d: ", i + 1);
                int n = sc.nextInt();
                tabla1[i] = n;
            }

            System.out.printf("\tINGRESA %d NÚMEROS PARA LA SEGUNDA TABLA: \n", tabla2.length);
            for (int i = 0; i < tabla2.length; i++) {
                System.out.printf("Número %d: ", i + 1);
                int n = sc.nextInt();
                tabla2[i] = n;
            }

            System.out.println("\tTABLA NÚMERO 3:");
            for (int i = 0; i < tabla3.length; i++) {
                tabla3[i] = (i % 2 == 0) ? tabla1[i / 2] : tabla2[i / 2];
                System.out.printf("Posición %d: %d\n", i + 1, tabla3[i]);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}