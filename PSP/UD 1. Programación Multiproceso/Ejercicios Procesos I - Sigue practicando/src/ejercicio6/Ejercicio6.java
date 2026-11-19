package ejercicio6;

public class Ejercicio6 {
    public static long sumar(int n1, int n2) {
        int min = Math.min(n1, n2);
        int max = Math.max(n1, n2);

        long suma = 0;
        for (int i = min; i <= max; i++) {
            suma += i;
        }
        return suma;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Uso: java Sumador <n1> <n2>");
            System.exit(1);
        }

        try {
            int n1 = Integer.parseInt(args[0]);
            int n2 = Integer.parseInt(args[1]);
            System.out.println("Suma de " + n1 + " a " + n2 + " = " + sumar(n1, n2));
        } catch (NumberFormatException e) {
            System.err.println("Error: los argumentos deben ser enteros.");
            System.exit(1);
        }
    }
}