package ejercicio11;

import java.io.Serializable;

public class Cliente implements Serializable {
  private int codigo;
  private String nombre;
  private String telefono;
  private String email;
  private double saldoPendiente;

  public Cliente(int codigo, String nombre, String telefono, String email, double saldoPendiente) {
    this.codigo = codigo;
    this.nombre = nombre;
    this.email = email;
    this.saldoPendiente = saldoPendiente;
    this.telefono = telefono;
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public double getSaldoPendiente() {
    return saldoPendiente;
  }

  public void setSaldoPendiente(double saldoPendiente) {
    this.saldoPendiente = saldoPendiente;
  }

  @Override
  public String toString() {
    return "Cliente [codigo=" + codigo + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email
        + ", saldoPendiente=" + saldoPendiente + "]";
  }

}
