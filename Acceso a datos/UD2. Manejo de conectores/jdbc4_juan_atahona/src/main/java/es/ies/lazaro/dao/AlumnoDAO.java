package es.ies.lazaro.dao;

import java.sql.SQLException;
import java.util.List;

import es.ies.lazaro.model.Alumno;

public interface AlumnoDAO {
  Alumno buscarPorId(int id) throws SQLException;

  List<Alumno> listar() throws SQLException;

  int insertar(Alumno alumno) throws SQLException;

  boolean actualizar(Alumno alumno) throws SQLException;

  boolean eliminar(int id) throws SQLException;
}