public class Alumno {
  private String nombre;
  private double notaPSP;

  public Alumno(String nombre, double notaPSP) {
    this.nombre = nombre;
    this.notaPSP = notaPSP;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public double getNotaPSP() {
    return notaPSP;
  }

  public void setNotaPSP(double notaPSP) {
    this.notaPSP = notaPSP;
  }

  @Override
  public String toString() {
    return "Alumno [nombre=" + nombre + ", notaPSP=" + notaPSP + "]";
  };

}
