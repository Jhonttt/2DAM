package es.ies.lazaro.model;

import java.time.LocalDate;

public class Alumno {
  private int nia;
  private String name;
  private String lastName;
  private String gender;
  private LocalDate birthDate;
  private String cycle;
  private String course;
  private String group;

  public Alumno() {
  }

  public Alumno(int nia, String name, String lastName, String gender, LocalDate birthDate, String cycle, String course,
      String group) {
    this.nia = nia;
    this.name = name;
    this.lastName = lastName;
    this.gender = gender;
    this.birthDate = birthDate;
    this.cycle = cycle;
    this.course = course;
    this.group = group;
  }

  public int getNia() {
    return nia;
  }

  public void setNia(int nia) {
    this.nia = nia;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getCycle() {
    return cycle;
  }

  public void setCycle(String cycle) {
    this.cycle = cycle;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  @Override
  public String toString() {
    return "Alumno [nia=" + nia + ", name=" + name + ", lastName=" + lastName + ", gender=" + gender + ", birthDate="
        + birthDate + ", cycle=" + cycle + ", course=" + course + ", group=" + group + "]";
  }

}
