package ejercicio11;

public class Alumno {
  private int id;
  private String name;
  private int age;
  private String ciclo;

  public Alumno(int id, String name, int age, String ciclo) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.ciclo = ciclo;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getCiclo() {
    return ciclo;
  }

  public void setCiclo(String ciclo) {
    this.ciclo = ciclo;
  }

  @Override
  public String toString() {
    return "Alumno [id=" + id + ", name=" + name + ", age=" + age + ", ciclo=" + ciclo + "]";
  }

}
