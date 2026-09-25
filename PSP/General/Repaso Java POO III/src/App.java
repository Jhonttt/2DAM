import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Jugador j1 = new Jugador("Messi", 10, 5);
        Jugador j2 = new Jugador("De gea", 1, 0);
        EquipoFutbol barca = new EquipoFutbol("Fc Barcelona", "Barcelona", new ArrayList<>(List.of(j1)));
        barca.aniadirJugador(j2);

        System.out.println(barca.toString());

    }
}
