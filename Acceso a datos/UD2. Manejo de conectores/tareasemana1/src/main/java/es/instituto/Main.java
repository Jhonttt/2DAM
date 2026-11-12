package es.instituto;

import java.util.List;

public class Main {
  public static void main(String[] args) {; 

    List<Alumno> alumnoSelecteds = AlumnoDAO.selectAlumnos();
    System.out.println("Ejercicio 2: ");
    alumnoSelecteds.stream().forEach(System.out::println);

    List<Alumno> alumnoSelectedsByCicle = AlumnoDAO.selectAlumnosByCicle("DAW");
    System.out.println("Ejercicio 3: ");
    if (alumnoSelectedsByCicle.isEmpty()) {
      System.out.println("No hay alumnos seleccionados para este ciclo.");
    } else {
      alumnoSelectedsByCicle.forEach(System.out::println);
    }

    List<Alumno> alumnoSelectedsByCicleAndAge = AlumnoDAO.selectAlumnosByCicleAndAge("DAM", 21);
    System.out.println("Ejercicio 4: ");
    if (alumnoSelectedsByCicleAndAge.isEmpty()) {
      System.out.println("No hay alumnos seleccionados para ese ciclo con ese rango de edad");
    } else {
      alumnoSelectedsByCicleAndAge.stream().forEach(System.out::println);
    }

    System.out.println("Ejercicio 5: ");
    AlumnoDAO.selectSummaryStudents();

  }
}