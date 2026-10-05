import java.io.*;

public class Ejecutador {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 0) {
            System.out.printf("Valor devuelto por waitFor(): %d", -1);
            System.exit(-1); 
        } 

        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "LeerNombre", args[args.length - 1]);

        Process p = pb.start();

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String linea;

            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        } 

        int codigo = p.waitFor();
        System.out.printf("Valor devuelto por waitFor(): %d", codigo);

    }
}
