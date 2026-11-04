package es.ies.lazaro.model;

public class Alumno {
  private int id;
  private String name;
  private int age;
  private String cycle;

  public Alumno() {
  }

  public Alumno(int id, String name, int age, String cycle) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.cycle = cycle;
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

  public String getCycle() {
    return cycle;
  }

  public void setCycle(String cycle) {
    this.cycle = cycle;
  }

  @Override
  public String toString() {
    return "Alumno [id=" + id + ", name=" + name + ", age=" + age + ", cycle=" + cycle + "]";
  }

}
