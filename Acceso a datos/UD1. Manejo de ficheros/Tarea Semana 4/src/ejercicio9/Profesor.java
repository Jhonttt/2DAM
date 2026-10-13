package ejercicio9;

import java.io.Serializable;

public class Profesor implements Serializable {
  private static final long serialVersionUID = 1L;
  private String nombre;
  private String email;

  public Profesor(String email, String nombre) {
    this.email = email;
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Profesor [nombre=" + nombre + ", email=" + email + "]";
  }

}
