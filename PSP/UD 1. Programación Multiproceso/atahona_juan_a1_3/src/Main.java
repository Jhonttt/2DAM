import java.util.Locale;

/**
 * Suma dos números recibidos por argumentos y muestra el resultado en la consola.
 */
public class Main {
    /** Posición del primer número en el array de argumentos. */
    private static final int ARG1 = 0;
    /** Posición del segundo número en el array de argumentos. */
    private static final int ARG2 = 1;

    /** Código ANSI que restablece el formato del texto en la consola. */
    private static final String RESET = "\u001B[0m";
    /** Código ANSI para mostrar los mensajes de error en rojo. */
    private static final String RED = "\u001B[31m";
    /** Código ANSI para mostrar el resultado de la suma en verde. */
    private static final String GREEN = "\u001B[32m";
    /** Código ANSI para mostrar los números y el signo de suma en azul. */
    private static final String BLUE = "\u001B[34m";

    /**
     * Crea una instancia de Main. La suma se realiza desde {@link #main(String[])}.
     */
    public Main() {
    }

    /**
     * Cambia las comas por puntos para poder leer números con decimales.
     *
     * @param num texto que contiene el número
     * @return el texto con puntos como separadores decimales
     */
    private static String normalize(String num) {
        return num.replace(',', '.');
    }

    /**
     * Comprueba si el texto se puede convertir en un número finito.
     * Acepta tanto la coma como el punto para los decimales.
     *
     * @param num texto que se quiere comprobar
     * @return {@code true} si es un número válido; {@code false} si no lo es,
     *         es infinito o es {@code NaN}
     */
    private static boolean isNumber(String num) {
        try {
            return Double.isFinite(Double.parseDouble(normalize(num)));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Valida los dos argumentos y muestra su suma con dos decimales.
     * Termina con código 0 si la operación se realiza o con código 2 si los
     * argumentos no son válidos.
     *
     * @param args los dos números que se quieren sumar
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
