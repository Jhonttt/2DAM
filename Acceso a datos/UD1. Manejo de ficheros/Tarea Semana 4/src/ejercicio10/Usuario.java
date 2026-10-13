package ejercicio10;

import java.io.Serializable;

public class Usuario implements Serializable {
  private static final long serialVersionUID = 1L;
  private String nombre;
  private String email;

  // transient indica que este atributo no se serializa, así que la contraseña
  // nunca se escribe en el fichero. Al deserializar, Java no ejecuta el
  // constructor de Usuario y deja los atributos transient con su valor por
  // defecto: null en los objetos, 0 en los números y false en los boolean. Por
  // eso, después de leer el usuario, password vale null. Nombre y email sí se
  // recuperan con sus valores originales.

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
