package ejercicio12;

import java.io.Serializable;

public class Alumno implements Serializable {
  private static final long serialVersionUID = 1L;
  private int numeroExpediente;
  private String nombre;
  private String apellidos;
  private String ciclo;
  private double notaMedia;

  public Alumno(String apellidos, String ciclo, String nombre, double notaMedia, int numeroExpediente) {
    this.apellidos = apellidos;
    this.ciclo = ciclo;
    this.nombre = nombre;
    this.notaMedia = notaMedia;
    this.numeroExpediente = numeroExpediente;
  }

  public int getNumeroExpediente() {
    return numeroExpediente;
  }

  public void setNumeroExpediente(int numeroExpediente) {
    this.numeroExpediente = numeroExpediente;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getCiclo() {
    return ciclo;
  }

  public void setCiclo(String ciclo) {
    this.ciclo = ciclo;
  }

  public double getNotaMedia() {
    return notaMedia;
  }

  public void setNotaMedia(double notaMedia) {
    this.notaMedia = notaMedia;
  }

  @Override
  public String toString() {
    return "Alumno [numeroExpediente=" + numeroExpediente + ", nombre=" + nombre + ", apellidos=" + apellidos
        + ", ciclo=" + ciclo + ", notaMedia=" + notaMedia + "]";
  }

}
