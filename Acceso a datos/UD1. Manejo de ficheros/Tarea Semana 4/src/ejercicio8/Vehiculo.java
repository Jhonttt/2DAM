package ejercicio8;

import java.io.Serializable;

public class Vehiculo implements Serializable {
  private static final long serialVersionUID = 1L;
  private String matricula;
  private String marca;
  private String modelo;
  private int kilometros;
  private double costeReparacion;

  public Vehiculo(String matricula, String marca, String modelo, int kilometros, double costeReparacion) {
    this.matricula = matricula;
    this.marca = marca;
    this.modelo = modelo;
    this.kilometros = kilometros;
    this.costeReparacion = costeReparacion;
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public int getKilometros() {
    return kilometros;
  }

  public void setKilometros(int kilometros) {
    this.kilometros = kilometros;
  }

  public double getCosteReparacion() {
    return costeReparacion;
  }

  public void setCosteReparacion(double costeReparacion) {
    this.costeReparacion = costeReparacion;
  }

  @Override
  public String toString() {
    return "Vehiculo [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", kilometros=" + kilometros
        + ", costeReparacion=" + costeReparacion + "]";
  }

}
