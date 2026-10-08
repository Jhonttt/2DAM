import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class Ejercicio1 {

    private static final Double[] temperatures = { 18.5, 19.2, 21.7, 23.1, 22.8, 20.4 };

    private static Map<String, Double> minMaxAvg() {
        double sum = 0;
        double max, min;
        max = min = temperatures[0];

        for (Double t : temperatures) {
            max = Math.max(max, t);
            min = Math.min(min, t);
            sum += t;
        }

        return Map.of(
                "avg", sum / temperatures.length,
                "max", max,
                "min", min);
    }

    public static void main(String[] args) throws Exception {
        Map<String, Double> result = minMaxAvg();

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("temperaturas.dat"))) {
            dos.writeInt(temperatures.length);
            for (Double t : temperatures) {
                dos.writeDouble(t);
            }

            dos.writeDouble(result.get("avg"));
            dos.writeDouble(result.get("max"));
            dos.writeDouble(result.get("min"));
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream("temperaturas.dat"))) {
            int n = dis.readInt();
            System.out.println("Temperaturas:");
            for (int i = 0; i < n; i++) {
                System.out.printf("  %.2f%n", dis.readDouble());
            }

            double avg = dis.readDouble();
            double max = dis.readDouble();
            double min = dis.readDouble();

            System.out.printf("Media: %.2f%nMáxima: %.2f%nMínima: %.2f%n", avg, max, min);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}
