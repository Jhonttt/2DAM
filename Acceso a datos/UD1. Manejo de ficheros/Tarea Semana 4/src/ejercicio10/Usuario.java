package ejercicio10;

import java.io.Serializable;

public class Usuario implements Serializable {
  private static final long serialVersionUID = 1L;
  private String nombre;
  private String email;
  private transient String password;

  public Usuario(String nombre, String email, String password) {
    this.nombre = nombre;
    this.email = email;
    this.password = password;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "Usuario [nombre=" + nombre + ", email=" + email + ", password=" + password + "]";
  }

}
