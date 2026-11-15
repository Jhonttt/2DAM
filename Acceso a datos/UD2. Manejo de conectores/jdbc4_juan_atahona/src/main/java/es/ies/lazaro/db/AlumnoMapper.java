package es.ies.lazaro.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.ies.lazaro.model.Alumno;

public class AlumnoMapper {

  public static Alumno mapear(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    String nombre = rs.getString("nombre");
    int edad = rs.getInt("edad");
    String ciclo = rs.getString("ciclo");
    return new Alumno(id, nombre, edad, ciclo);
  }
}