import java.util.ArrayList;
import java.util.List;

public class EquipoFutbol {
  private String nombre;
  private String ciudad;
  private List<Jugador> jugadores;

  public EquipoFutbol(String nombre, String ciudad, List<Jugador> jugadores) {
    this.nombre = nombre;
    this.ciudad = ciudad;
    this.jugadores = jugadores;
  }

  public EquipoFutbol(String nombre, String ciudad, Jugador j) {
    this.nombre = nombre;
    this.ciudad = ciudad;
    this.jugadores = new ArrayList<>();
    this.jugadores.add(j);
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCiudad() {
    return ciudad;
  }

  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }

  public List<Jugador> getJugadores() {
    return jugadores;
  }

  public void setJugadores(List<Jugador> jugadores) {
    this.jugadores = jugadores;
  }

  public void aniadirJugador(Jugador jugador) {
    this.jugadores.add(jugador);
  }

  @Override
  public String toString() {
    return "EquipoFutbol [nombre=" + nombre + ", ciudad=" + ciudad + ", jugadores=" + jugadores + "]";
  }

  

}
