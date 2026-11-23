package es.ies.di.ud3;

import es.ies.di.ud3.view.ContadorVisual;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Creamos el componente contadorVisual
        ContadorVisual c = new ContadorVisual(10);

        // Mostramos la etiqueta con el primer valor del primer contador.
        Label estado = new Label("Primer valor del contador 1: " + c.getValor());

        // Ahora mostramos la etiqueta actualizada tras pulsar el botón de incremento o decremento del primer contador.
        c.valorProperty().addListener((obs, oldVal, newVal) -> {
            estado.setText("Valor del primer contador actualizado: " + newVal);
        });
        // Creamos el contenedor principal con todos los elementos visuales separados
        // por 15px. Quedaría así en modo vertical:
        // Titulo
        // Etiqueta: Primer contador
        // Componente: Primer contador
        // Etiqueta: Segundo contador
        // Componente: Segundo contador
        // Etiqueta: Estado del primer contador
        VBox raiz = new VBox(15,
            new Label("Ejemplo de componente reutilizables"),
            new Label("Primer contador"),
            c,
            new Label("Segundo contador: "),
            new ContadorVisual(20),
            estado); // Creamos el contenedor principal (Vertical)

        // raiz.setPadding(new Insets(20));
        raiz.setAlignment(Pos.CENTER);

        // La escena que contiene todos los elementos visuales
        // que se mostrarán en la ventana (tamaño 400x300)
        Scene escena = new Scene(raiz, 400, 300);

        stage.setTitle("UD3 - ContadorVisual");

        // Indicamos la escena a mostrar dentro del stage (que es la ventana)
        stage.setScene(escena);

        stage.setMinHeight(150);
        stage.setMinWidth(200);

        // Mostramos la ventana en pantalla
        stage.show();
    }

    public static void main(String[] args) {
        // Con el método launch iniciamos el ciclo de vida de la app en JavaFX
        // Recordar que termina llamando el método start definido arriba
        launch(args);
    }
}