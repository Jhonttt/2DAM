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

public class AlumnoDAOImpl implements AlumnoDAO {
  private static final String SELECT = "SELECT id, nombre, edad, ciclo FROM alumnos";
  private static final String INSERT = "INSERT INTO alumnos (nombre, edad, ciclo) VALUES (?, ?, ?)";

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
    String sql = SELECT + " WHERE ciclo = ? AND edad >= ? ORDER BY nombre ASC, id ASC";
    try (Connection cn = ConnectionDB.getConnection();
        PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setString(1, ciclo);
      ps.setInt(2, edadMin);
      return read(ps);
    }
  }

  @Override
  public List<Integer> anadirAlumnos(List<AlumnoXML> alumnos, boolean fallarTrasPrimero)
      throws SQLException {
    List<Integer> keysList = new ArrayList<>();
    if (alumnos == null || alumnos.isEmpty()) {
      return keysList; // lote vacío: no se abre conexión ni se escribe
    }

    try (Connection cn = ConnectionDB.getConnection()) {
      cn.setAutoCommit(false);
      try (PreparedStatement ps = cn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
        int insertados = 0;
        for (AlumnoXML a : alumnos) {
          ps.setString(1, a.getNombre());
          ps.setInt(2, a.getEdad());
          ps.setString(3, a.getCiclo());

          if (ps.executeUpdate() != 1) {
            throw new SQLException("La inserción de '" + a.getNombre() + "' no afectó a 1 fila");
          }
          try (ResultSet keys = ps.getGeneratedKeys()) {
            if (!keys.next()) {
              throw new SQLException("No se devolvió clave para '" + a.getNombre() + "'");
            }
            keysList.add(keys.getInt(1));
          }

          insertados++;
          if (fallarTrasPrimero && insertados == 1) {
            throw new SQLException("Fallo de prueba tras el primer INSERT");
          }
        }
        cn.commit();
        return keysList; // el llamador solo recibe los IDs si el commit se ha hecho
      } catch (SQLException | RuntimeException e) {
        cn.rollback();
        throw e;
      }
    }
  }

  @Override
  public boolean existeNombre(String nombre) throws SQLException {
    String sql = SELECT + " WHERE nombre = ?";
    try (Connection cn = ConnectionDB.getConnection();
        PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setString(1, nombre);
      return !read(ps).isEmpty();
    }
  }
}