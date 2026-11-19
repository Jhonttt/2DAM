package ejercicio6;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Executer6 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String n1 = args.length >= 2 ? args[0] : "1";
        String n2 = args.length >= 2 ? args[1] : "10";

        ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "ejercicio6.Ejercicio6", n1, n2);
        pb.directory(new File("."));

        Process p = pb.start();

        try (BufferedReader out = new BufferedReader(new InputStreamReader(p.getInputStream()));
                BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {

            String linea;
            while ((linea = out.readLine()) != null) {
                System.out.println("Hijo dice: " + linea);
            }
            while ((linea = err.readLine()) != null) {
                System.err.println("Hijo error: " + linea);
            }
        }

        int codigo = p.waitFor();
        System.out.println("Código de salida: " + codigo);
    }
}