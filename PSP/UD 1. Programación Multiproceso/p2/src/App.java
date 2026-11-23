import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * App
 */
public class App {

    private static final String HIJA = Randoms.class.getName();

    public static void main(String[] args) {
        String cp = System.getProperty("java.class.path");
        Process p = null;

        try {
            List<String> cmd = new ArrayList<>(List.of("java", "-cp", cp, HIJA));
            cmd.addAll(Arrays.asList(args));

            p = new ProcessBuilder(cmd).redirectErrorStream(true).start();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String l;
                while ((l = br.readLine()) != null) {
                    System.out.println(l);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}