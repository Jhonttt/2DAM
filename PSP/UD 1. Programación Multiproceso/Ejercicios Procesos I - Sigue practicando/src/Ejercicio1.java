
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class Ejercicio1 {
    private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[91m";
    private static final String GREEN = "\u001B[92m";
    private static final String BLUE = "\u001B[94m";
    private static final String WHITE = "\u001B[97m";

    private static String normalize(String num) {
        return num.replace(",", ".");
    }

    private static boolean isNumber(String num) {
        try {
            return Double.isFinite(Double.parseDouble(normalize(num)));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.print(BLUE + "Introduce un número del cual se sacaran los divisores: " + WHITE);

        String n1 = BR.readLine();

        if (!isNumber(n1)) {
            System.out.println(RED + "Introduce un argumento válido." + RESET);
            System.exit(2);
        }

        double n = Double.parseDouble(normalize(n1));

        System.out.printf(Locale.ROOT, "%sDivisores de %.2f: %s%n", WHITE, n ,RESET);
        for (int i = 0; i <= n; i++) {
            if (n % i == 0)
                System.out.println(GREEN + "- " + i + RESET);
        }

    }
}
