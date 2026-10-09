package es.ies.javafx.gestortareas;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * ACTIVIDAD 1 - GESTOR DE TAREAS CON JAVAFX
 * 
 * Conceptos principales:
 * - Application, Stage, Scene
 * - VBox y HBox
 * - TextField, ComboBox, ListView, Button y Label
 * - Propiedades de componentes
 * - Eventos con setOnAction()
 * - Listeners sobre propiedades
 * - SelectionModel
 * - Alert
 * - Validación y feedback
 * 
 * La aplicación hereda de Application, la clase base de JavaFX
 * 
 * El flujo de arranque de una aplicación JavaFX es:
 * main () -> launch() -> JavaFx inicia su entorno -> start (Stage state) -> Se
 * construye la interfaz -> stage.show()
 */

// La clase Application de la que debe heredar cualquier App con main en javafx
// es la clase que permite ejecutar una aplicación JavaFX
public class App extends Application {

    // Atributos de la clase.
    private ComboBox<String> comboPrioridad;
    private ListView<String> listaTareas;
    private Button botonCompletar;
    private Button botonEliminar;
    private Label mensaje;
    private Label estado;
    private TextField campoTarea;
    private static final String TICK = "✔ ";

    /**
     * En el flujo de trabajo de JavaFX, se llama automáticamente a este método
     * start
     * En este método construimos toda la interfaz
     * 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage stage) {
        // 1.- Creamos una etiqueta y se modifica su estilo vía CSS
        Label titulo = new Label("Gestor de tareas");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // 2.- Creamos un TextField para introducir el texto de la tarea
        campoTarea = new TextField();
        // El método setPromptText es un método que muestra el texto cuando el TextField
        // está vacío
        campoTarea.setPromptText("Escribe una tarea");
        // 3.- Permitir que este TextField pueda crecer horizontalmente cuando se
        // redimensione la ventana. Se aplica a campoTarea y se hace siempre.
        HBox.setHgrow(campoTarea, Priority.ALWAYS);

        // 4.- Creamos un desplegable con tres prioridades: baja, media y alta.
        // Queremos que se quede seleccionada la prioridad media.
        comboPrioridad = new ComboBox<>();
        comboPrioridad.getItems().addAll("Baja", "Media", "Alta");
        comboPrioridad.setValue("Media");

        // 5.- Añadimos un botón para añadir la tarea. Es el botón predeterminado de la
        // ventana.
        Button botonAnadir = new Button("Añadir tarea");
        botonAnadir.setDefaultButton(true);

        // 6.- Creamos un contenedor horizontal para colocar los elementos.
        // 10 representa el espaciado entre elementos.
        HBox formulario = new HBox(10,
                new Label("Nueva tarea:"),
                campoTarea,
                new Label("Prioridad:"),
                comboPrioridad,
                botonAnadir);

        // 7.- Alineo los elementos hacia la izquierda y centrados verticalmente.
        formulario.setAlignment(Pos.CENTER_LEFT);

        // 8.- Creamos una lista donde se van a mostrar las tarea creadas.
        listaTareas = new ListView<>();

        // 9.- Ancho que va tener el componente de la lista de tareas.
        listaTareas.setPrefHeight(200);

        // 10.- Añadimos una lista de botones.
        botonCompletar = new Button("Marcar como completada"); // Permitirá marcar como compeltada una tarea
        botonEliminar = new Button("Eliminar"); // Elimina una tarea
        Button botonLimpiar = new Button("Limpiar todas"); // Limpia todas las tareas

        // 11.- Defino un contenedor horizontal para poner todos los botones
        HBox acciones = new HBox(10,
                botonCompletar,
                botonEliminar,
                botonLimpiar);

        // 12.- Lo alinea al centro y a la izquierda
        acciones.setAlignment(Pos.CENTER_LEFT);

        // 13.- Defino dos labels para mostrar información
        mensaje = new Label();
        mensaje.setWrapText(true); // ¿Qué hará esto?
        estado = new Label();

        // 14.- Defino mi elemento raíz de la ventana donde voy a ir apilando
        // (verticalmente) cada uno de los elementos gráficos de la ventana.

        VBox raiz = new VBox(14,
                titulo,
                formulario,
                new Label("Lista de tareas"),
                listaTareas,
                acciones,
                mensaje,
                estado);

        // 15.- Añadimos 20 pixeles de margen inferior, superior, derecho e izquierdo
        raiz.setPadding(new Insets(20));

        // 16.- Permitimos que la lista crezca verticalmente si crece la ventana
        VBox.setVgrow(listaTareas, Priority.ALWAYS);

        // 17.- Añadimos eventos a los botones usando expresiones lambda.
        botonAnadir.setOnAction(e -> anadirTarea()); // Cuando se pulse un botón se ejecuta el código del método
                                                     // anadirTarea

        botonEliminar.setOnAction(e -> eliminarTarea());
        botonCompletar.setOnAction(e -> completarTarea());
        botonLimpiar.setOnAction(e -> limpiarTareas());

        // Necesito una escena para qeu se muestre y llamar al método stage.show();
        Scene scene = new Scene(raiz, 820, 500); // Defino el contenido: el contenido va a ser el elemento formulario
        stage.setTitle("Gestor de tareas en JavaFX"); // Le pongo título a la ventana
        stage.setScene(scene); // Asigno el contenido (scene) a la ventana (stage)
        stage.show(); // Muestro la ventana con un show

    }

    /**
     * Método que se ejecuta cuando se pulsa el botón botonAnadir
     * Realiza una validación sobre el campo de datos de entrada de la tarea
     * Se añade al listView (listaTareas)
     * Se muestra un mensasje y se actualiza el estado de la aplicación
     */
    private void anadirTarea() {
        String texto = campoTarea.getText().trim();

        if (texto.isBlank()) {
            mostrarMensaje("Introduce una tarea. No se permiten tareas vacías.");
            return;
        }

        // Transformamos el texto de la tarea precediendo de la propiedad
        String proridad = comboPrioridad.getValue();
        String tarea = "[" + proridad + "] " + texto;

        listaTareas.getItems().add(tarea); // Añadimos la tarea al listView
        listaTareas.getSelectionModel().selectLast(); // ¿¿¿¿¿¿¿???????
        listaTareas.scrollTo(listaTareas.getItems().size() - 1); // ¿¿¿¿¿¿¿¿¿???????

        campoTarea.clear(); // Limpiamos el TextField de la tarea
        comboPrioridad.setValue("Media"); // Volvemos a dejar la prioridad en Media en la lista de desplegable
        mostrarMensaje("Tarea añadida"); // Mostramos un mensaje de tarea añadida correctamente
        campoTarea.requestFocus(); // Devolvemos el foco al TextField
    }

    private void eliminarTarea() {
        int selected = listaTareas.getSelectionModel().getSelectedIndex();
        if (selected == -1) {
            mostrarMensaje("Se tiene que selecionar una tarea");
            return;
        }
        listaTareas.getItems().remove(selected);
        mostrarMensaje("Tarea borrada");
    }

    private void completarTarea() {
        int selected = listaTareas.getSelectionModel().getSelectedIndex();
        if (selected == -1) {
            mostrarMensaje("Se tiene que selecionar una tarea");
            return;
        }

        String tarea = listaTareas.getItems().get(selected);

        if (tarea.startsWith(TICK)) {
            listaTareas.getItems().set(selected, tarea.substring(TICK.length()));
            mostrarMensaje("Tarea marcada como pendiente");
            return;
        }

        listaTareas.getItems().set(selected, TICK + tarea);
        mostrarMensaje("Tarea completada");
    }

    private void limpiarTareas() {
        boolean borradas = listaTareas.getItems().removeIf(t -> t.startsWith("✔ "));
        if (!borradas) {
            mostrarMensaje("No hay tareas completadas");
        }
    }

    private void mostrarMensaje(String text) {
        this.mensaje.setText(text);
    }

    public static void main(String[] args) {
        launch();
    }
}
