package es.ieslazarocardenas.di.actividad1;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * Gestor de tareas con JavaFX
 * Qué elementos gráficos voy a utilizar?
 * TextField, ComboBox, ListView,  Button y Label
 * VBox y HBox para orientación vertical y horizontal
 * 
 * Eventos (setOnAction) para hacer algo cuando el usuario pulse un botón.
 * Listeners...
 * 
 * ¿Cuál es el flujo de la aplicación en JavaFX?
 * 
 * main() -> launch() -> JavaFX inicia su entorno -> start (Stage stage) -> se construye la interfaz -> stage.show()
 */

public class App extends Application {
    /**    (non-Javadoc)
     * Este método se llama automáticamente por parte de JavaFX cuando se inicia el contexto de JavaFx.
     * Este método contendrá todos los componentes gráficos.
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage stage) {
        // Añadimos una etiqueta que va a ser el título de la ventana. Modificamos su estilo con CSS.
        Label titulo = new Label("Gestor de tareas");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold");

        TextField campoTarea = new TextField(); // Para introducir la descripción de la tarea.
        campoTarea.setPromptText("Escribe una tarea."); // setPromptText es un método que aparece mientras el campo está vacío.
        HBox.setHgrow(campoTarea, Priority.ALWAYS); // Indico que el campo puede crecer horizontalmente dentro del HBox.
    }
    
    public static void main(String[] args) {
        
    }

}