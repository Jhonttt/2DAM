package es.ies.lazaro.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.ies.lazaro.dao.AlumnoDAO;
import es.ies.lazaro.dao.AlumnoMapper;
import es.ies.lazaro.db.ConnectionDB;
import es.ies.lazaro.model.Alumno;

public class AlumnoDAOImpl implements AlumnoDAO {
  private static final String SELECT = "SELECT id, nombre, edad, ciclo FROM alumnos";

  @Override
  public Alumno buscarPorId(int id) throws SQLException {
    String sql = SELECT + " WHERE id = ?";
    try (Connection cn = ConnectionDB.getConnectionDB();
        PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setInt(1, id);
      return read(ps).stream().findFirst().orElse(null);
    }
  }

  @Override
  public List<Alumno> listar() throws SQLException {
    String sql = SELECT + " ORDER BY id";
    try (Connection cn = ConnectionDB.getConnectionDB();
        PreparedStatement ps = cn.prepareStatement(sql)) {
      return read(ps);
    }
  }

  @Override
  public int insertar(Alumno alumno) throws SQLException {
    String sql = "INSERT INTO alumnos (nombre, edad, ciclo) VALUES (?, ?, ?)";
    try (Connection cn = ConnectionDB.getConnectionDB();
        PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      ps.setString(1, alumno.getNombre());
      ps.setInt(2, alumno.getEdad());
      ps.setString(3, alumno.getCiclo());

      if (ps.executeUpdate() != 1) {
        throw new SQLException("Inserción no realizada");
      }
      try (ResultSet keys = ps.getGeneratedKeys()) {
        if (keys.next()) {
          return keys.getInt(1);
        }
        throw new SQLException("No se recuperó el ID generado");
      }
    }
  }

  @Override
  public boolean actualizar(Alumno alumno) throws SQLException {
    String sql = "UPDATE alumnos SET nombre = ?, edad = ?, ciclo = ? WHERE id = ?";
    try (Connection cn = ConnectionDB.getConnectionDB();
        PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setString(1, alumno.getNombre());
      ps.setInt(2, alumno.getEdad());
      ps.setString(3, alumno.getCiclo());
      ps.setInt(4, alumno.getId());
      return ps.executeUpdate() > 0;
    }
  }

  @Override
  public boolean eliminar(int id) throws SQLException {
    String sql = "DELETE FROM alumnos WHERE id = ?";
    try (Connection cn = ConnectionDB.getConnectionDB();
        PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setInt(1, id);
      return ps.executeUpdate() > 0;
    }
  }

  private static List<Alumno> read(PreparedStatement ps) throws SQLException {
    List<Alumno> alumnos = new ArrayList<>();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        alumnos.add(AlumnoMapper.mapear(rs));
      }
    }
    return alumnos;
  }
}