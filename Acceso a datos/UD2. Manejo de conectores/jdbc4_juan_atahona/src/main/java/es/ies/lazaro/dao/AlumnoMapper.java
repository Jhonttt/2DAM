package es.ies.lazaro.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.ies.lazaro.model.Alumno;

public final class AlumnoMapper {
  private AlumnoMapper() {}

  public static Alumno mapear(ResultSet rs) throws SQLException {
    return new Alumno(
        rs.getInt("id"),
        rs.getString("nombre"),
        rs.getInt("edad"),
        rs.getString("ciclo"));
  }
}