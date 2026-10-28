package es.instituto;

import java.util.List;

public class Main {
  public static void main(String[] args) {

    List<Alumno> alumnoSelecteds = AlumnoDAO.selectAlumnos();
    System.out.println("Ejercicio 1: ");
    alumnoSelecteds.stream().forEach(System.out::println);

    List<Alumno> alumnoSelectedsByCicle = AlumnoDAO.selectAlumnosByCicle("DAW");
    System.out.println("Ejercicio 2: ");
    if (alumnoSelectedsByCicle.isEmpty()) {
      System.out.println("No hay alumnos seleccionados para este ciclo.");
    } else {
      alumnoSelectedsByCicle.forEach(System.out::println);
    }

    

  }
}