public class Banco {
  private String nombre;
  private String CIF;
  private String direccion;

  public Banco(String nombre, String cIF, String direccion) {
    this.nombre = nombre;
    CIF = cIF;
    this.direccion = direccion;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCIF() {
    return CIF;
  }

  public void setCIF(String cIF) {
    CIF = cIF;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  @Override
  public String toString() {
    return "Banco [nombre=" + nombre + ", CIF=" + CIF + ", direccion=" + direccion + "]";
  }

}
