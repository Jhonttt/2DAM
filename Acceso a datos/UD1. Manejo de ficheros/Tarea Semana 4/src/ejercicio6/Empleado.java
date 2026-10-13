package ejercicio6;

import java.io.Serializable;

public class Empleado implements Serializable {
  private static final long serialVersionUID = 1L;
  private int id;
  private String nombre;
  private String departamentos;
  private double salario;

  public Empleado(int id, String nombre, String departamentos, double salario) {
    this.id = id;
    this.nombre = nombre;
    this.departamentos = departamentos;
    this.salario = salario;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDepartamentos() {
    return departamentos;
  }

  public void setDepartamentos(String departamentos) {
    this.departamentos = departamentos;
  }

  public double getSalario() {
    return salario;
  }

  public void setSalario(double salario) {
    this.salario = salario;
  }

  @Override
  public String toString() {
    return "Empleado [id=" + id + ", nombre=" + nombre + ", departamentos=" + departamentos + ", salario=" + salario
        + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
  }
}
