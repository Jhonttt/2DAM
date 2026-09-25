public class Jugador {
  private String nombre;
  private int dorsal;
  private int goles;

  public Jugador(String nombre, int dorsal, int goles) {
    this.nombre = nombre;
    this.dorsal = dorsal;
    this.goles = goles;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getDorsal() {
    return dorsal;
  }

  public void setDorsal(int dorsal) {
    this.dorsal = dorsal;
  }

  public int getGoles() {
    return goles;
  }

  public void setGoles(int goles) {
    this.goles = goles;
  }

}
