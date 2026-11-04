package es.ies.lazaro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jakewharton.fliptables.FlipTableConverters;

import es.ies.lazaro.db.ConnectionDB;
import es.ies.lazaro.utils.Colors;

public class AlumnoDAO {
  private AlumnoDAO() {
  }

  public static int createStudent(String name, int age, String cycle) {
    String sql = "INSERT INTO alumnos (nombre, edad, ciclo) VALUES (?, ?, ?)";
    try (Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      ps.setString(1, name);
      ps.setInt(2, age);
      ps.setString(3, cycle);

      int create = ps.executeUpdate();

      if (create == 0) {
        System.out.println(Colors.RED + "No se ha insertado el alumno " + name);
        return -1;
      }

      System.out.println(Colors.PURPLE + "Filas insertadas: " + create);

      try (ResultSet keys = ps.getGeneratedKeys()) {
        if (keys.next()) {
          int id = keys.getInt(1);
          System.out.println(Colors.PURPLE + "ID generado: " + id);
          return id;
        }
      }
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }
    return -1;
  }

  public static void findStudents(String cycle, int age) {
    String sql = "SELECT id, nombre, edad, ciclo FROM alumnos WHERE ciclo = ? AND edad >= ? ORDER BY nombre";
    try (Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setString(1, cycle);
      ps.setInt(2, age);

      try (ResultSet rs = ps.executeQuery()) {
        if (!rs.isBeforeFirst()) {
          System.out.println(Colors.RED + "No hay alumnos para mostrar");
          return;
        }
        System.out.println(Colors.WHITE + FlipTableConverters.fromResultSet(rs));
      }
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }
  }

  public static void updateStudentsCycle(String cycle, int id) {
    String sql = "UPDATE alumnos SET ciclo = ? WHERE id = ?";
    try (Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setString(1, cycle);
      ps.setInt(2, id);

      int updatedRows = ps.executeUpdate();

      if (updatedRows > 0) {
        System.out
            .println(Colors.PURPLE + "Se "
                + (updatedRows > 1 ? "han modificado " + Colors.GREEN + updatedRows + Colors.PURPLE + " filas"
                    : "ha modificado " + Colors.GREEN + "1" + Colors.PURPLE + " fila"));
        return;
      }

      System.out.println(Colors.RED + "El id " + id + " no existe");
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }
  }

  public static void deleteStudents(int id) {
    String sql = "DELETE FROM alumnos WHERE id = ?";
    try (Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setInt(1, id);

      int deletedRows = ps.executeUpdate();

      if (deletedRows > 0) {
        System.out
            .println(Colors.PURPLE + "Se "
                + (deletedRows > 1 ? "han borrado " + Colors.GREEN + deletedRows + Colors.PURPLE + " filas"
                    : "ha borrado " + Colors.GREEN + "1" + Colors.PURPLE + " fila"));
        return;
      }

      System.out.println(Colors.RED + "El id " + id + " no existe");
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }
  }

  public static int countStudentsById(int id) {
    String sql = "SELECT COUNT(*) FROM alumnos WHERE id = ?";
    try (Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setInt(1, id);

      try (ResultSet rs = ps.executeQuery()) {
        rs.next();
        return rs.getInt(1);
      }
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
      return 0;
    }
  }

  public static void findStudentById(int id) {
    String sql = "SELECT id, nombre, edad, ciclo FROM alumnos WHERE id = ?";
    try (Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setInt(1, id);

      try (ResultSet rs = ps.executeQuery()) {
        if (!rs.isBeforeFirst()) {
          System.out.println(Colors.RED + "No existe ningún alumno con el id " + id);
          return;
        }
        System.out.println(Colors.GREEN + "\n==== FICHA DE ALUMNO ====");
        System.out.println(Colors.WHITE + FlipTableConverters.fromResultSet(rs));
      }
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }
  }
}
