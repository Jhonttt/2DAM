package es.ies.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        Label etiqueta = new Label("Mi primera aplicación JavaFX");
        Button boton = new Button("Pulsar");

        boton.setOnAction(e -> {
            etiqueta.setText("¡Hola JavaFX!");
        });

        VBox raiz = new VBox(10);
        raiz.getChildren().addAll(etiqueta, boton);

        Scene escena = new Scene(raiz, 400, 250);

        stage.setTitle("Hola JavaFX");
        stage.setScene(escena);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}