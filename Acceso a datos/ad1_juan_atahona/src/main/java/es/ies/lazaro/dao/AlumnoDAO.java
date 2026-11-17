package es.ies.lazaro.dao;

import java.sql.SQLException;
import java.util.List;

import es.ies.lazaro.model.Alumno;
import es.ies.lazaro.model.AlumnoXML;

public interface AlumnoDAO {
  List<Alumno> alumnoCicloEdad(String ciclo, int edadMin) throws SQLException; 
  void anadirAlumnos(List<AlumnoXML> alumnos) throws SQLException; 
}
