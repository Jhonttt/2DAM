package es.ies.di.ud3.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * ContadorVisual es un nodo horizontal de JavaFx
 * De ahí que heredemos de HBox
 */
public class ContadorVisual extends HBox {

  // Añadimos los atributos de esta clase, que serán los botones y el label que se
  // mostrará en el componente.
  private final Button botonMenos = new Button("-");
  private final Button botonMas = new Button("+");
  private final Label etiquetaValor = new Label();

  // Un int guarda un valor, IntegerProperty guarda un valor y además puede avisar
  // de cambios y enlazarse con otros propiedades (bindings y listeners)
  private final IntegerProperty valor = new SimpleIntegerProperty();

  // Constructor
  public ContadorVisual(int num) {
    // Definimos el espacio entre los elementos del HBox
    setSpacing(10);

    // Alineamos los elementos dentro del HBox
    setAlignment(Pos.CENTER);

    // Añadimos los componentes/elementos al HBox
    getChildren().addAll(botonMenos, etiquetaValor, botonMas);

    setValor(num);

    // Eventos de los botones
    botonMas.setOnAction(e -> incrementar());
    botonMenos.setOnAction(e -> decrementar());

    // Inicializamos el valor del label
    // etiquetaValor.setText(String.valueOf(valor.get()));
    etiquetaValor.textProperty().bind(valor.asString());
    etiquetaValor.setTextFill(Color.BLUE);
  }

  // Métodos
  public int getValor() {
    return valor.get();
  }

  private void setValor(int nuevoValor) {
    valor.set(nuevoValor);
  }

  private void incrementar() {
    setValor(getValor() + 1);
  }

  private void decrementar() {
    if (getValor() > 0)
      setValor(getValor() - 1);
  }

  public IntegerProperty valorProperty() {
    return this.valor;
  }

}
