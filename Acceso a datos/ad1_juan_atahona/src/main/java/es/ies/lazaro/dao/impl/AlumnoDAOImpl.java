package es.ies.lazaro.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.ies.lazaro.dao.AlumnoDAO;
import es.ies.lazaro.db.ConnectionDB;
import es.ies.lazaro.model.Alumno;
import es.ies.lazaro.model.AlumnoXML;
import es.ies.lazaro.utils.Colors;

public class AlumnoDAOImpl implements AlumnoDAO {
  private static final String SELECT = "SELECT id, nombre, edad, ciclo FROM alumnos";

  private static Alumno mapper(ResultSet rs) throws SQLException {
    return new Alumno(
        rs.getInt(1),
        rs.getString(2),
        rs.getInt(3),
        rs.getString(4));
  }

  private static List<Alumno> read(PreparedStatement ps) throws SQLException {
    List<Alumno> alumnos = new ArrayList<>();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        alumnos.add(mapper(rs));
      }
    }
    return alumnos;
  }

  @Override
  public List<Alumno> alumnoCicloEdad(String ciclo, int edadMin) throws SQLException {
    String sql = SELECT + " WHERE ciclo = ? and edad >= ? ORDER BY nombre, id ASC";
    try (Connection cn = ConnectionDB.getConnectionDB();
        PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setString(1, ciclo);
      ps.setInt(2, edadMin);
      return read(ps);
    }
  }

  @Override
  public void anadirAlumnos(List<AlumnoXML> alumnos) throws SQLException {
    String sql = "INSERT INTO alumnos (nombre, edad, ciclo) VALUES (?, ?, ?)";
    List<Integer> keysList = new ArrayList<>();
    Connection cn = ConnectionDB.getConnectionDB();
    try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      cn.setAutoCommit(false);

      for (AlumnoXML a : alumnos) {
        ps.setString(1, a.getNombre());
        ps.setInt(2, a.getEdad());
        ps.setString(3, a.getCiclo());
        ps.executeUpdate();
        try (ResultSet keys = ps.getGeneratedKeys()) {
          while (keys.next()) {
            keysList.add(keys.getInt(1));
          }
        }
      }

      System.out.println(Colors.BLUE + "Claves generadas: ");
      for (Integer i : keysList) {
        System.out.println(Colors.WHITE + "- " + i);
      }

      cn.commit();
      cn.close();
    } catch (SQLException e) {
      cn.rollback();
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
      cn.close();
    } catch (Exception e) {
      cn.rollback();
      System.err.println(Colors.RED + "Error: " + e.getMessage());
      cn.close();
    }
  }

}
