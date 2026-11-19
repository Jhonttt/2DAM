import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main2 {
  public static void main(String[] args) throws Exception {
    String fichero = args.length > 0 ? args[0] : "texto.txt";
    String java = System.getProperty("java.home") + "/bin/java";
    String cp = System.getProperty("java.class.path");
    String vocales = "aeiou";

    List<Process> procesos = new ArrayList<>();
    List<Path> salidas = new ArrayList<>();

    // 1) Lanzar los 5 a la vez
    for (char v : vocales.toCharArray()) {
      Path salida = Files.createTempFile("vocal_" + v + "_", ".tmp");
      salidas.add(salida);

      ProcessBuilder pb = new ProcessBuilder(
          java, "-cp", cp, "ContadorVocal", fichero, String.valueOf(v), salida.toString());
      pb.redirectErrorStream(true); // sin esto, el stderr queda en un pipe que nadie lee
      procesos.add(pb.start());
    }

    // 2) Esperar a todos; si uno falla, mostramos lo que dijo
    for (int i = 0; i < procesos.size(); i++) {
      Process p = procesos.get(i);
      int exit = p.waitFor();
      if (exit != 0) {
        String msg = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        throw new IllegalStateException(
            "Falló el hijo de '" + vocales.charAt(i) + "' (exit " + exit + "): " + msg);
      }
    }

    // 3) Leer parciales, sumar y limpiar
    long total = 0;
    for (int i = 0; i < vocales.length(); i++) {
      long parcial = Long.parseLong(Files.readString(salidas.get(i)).trim());
      System.out.println(vocales.charAt(i) + ": " + parcial);
      total += parcial;
      Files.deleteIfExists(salidas.get(i));
    }
    System.out.println("Total vocales: " + total);
  }
}