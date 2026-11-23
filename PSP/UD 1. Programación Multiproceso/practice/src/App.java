import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import utils.Colors;

public class App {
    public static void main(String[] args) throws Exception {

        try {
            String cp = System.getProperty("java.class.path");
            String son = Mayusculas.class.getName();

            Process p = new ProcessBuilder("java", "-Dstdout.encoding=UTF-8", "-Dstderr.encoding=UTF-8", "-cp", cp, son)
                    .redirectErrorStream(true).start();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8));
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream(), StandardCharsets.UTF_8));
                    BufferedReader sc = new BufferedReader(
                            new InputStreamReader(System.in, StandardCharsets.UTF_8))) {

                System.out.println(Colors.BLUE + "Introduce líneas de texto. Introduce " + Colors.YELLOW + "\"Salir\""
                        + Colors.BLUE + " para finalizar." + Colors.WHITE);
                String line;
                while ((line = sc.readLine()) != null) {
                    if (line.equalsIgnoreCase("fin")) {
                        break;
                    }

                    bw.write(line);
                    bw.newLine();
                    bw.flush();

                    String respuesta = br.readLine();
                    if (respuesta != null) {
                        System.out.println(Colors.PURPLE + respuesta + Colors.WHITE);
                    }
                }
                br.close();
                bw.close();

                int exit_code = p.waitFor();
                System.out.println(Colors.GREEN + "Proceso hijo finalizado con código: " + Colors.PURPLE + exit_code);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(Colors.RED + "Error de intrerupción: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage());
            System.exit(1);
        }
    }
}
