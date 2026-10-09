import java.util.Locale;

public class Main {
    private static final int ARG1 = 0;
    private static final int ARG2 = 1;

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";

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
            return Double.isFinite(Double.parseDouble(normalize(num)));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(RED + "Introduce 2 números por argumento" + RESET);
            System.exit(2);
        }

        if (!isNumber(args[ARG1]) || !isNumber(args[ARG2])) {
            System.out.println(RED + "Ambos argumentos deben ser números" + RESET);
            System.exit(2);
        }

        double n1 = Double.parseDouble(normalize(args[ARG1]));
        double n2 = Double.parseDouble(normalize(args[ARG2]));

        System.out.printf(Locale.ROOT, "%s%.2f + %.2f = %s%.2f%s", BLUE, n1, n2, GREEN, n1 + n2, RESET);
        System.exit(0);
    }
}
