package es.ies.lazaro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jakewharton.fliptables.FlipTableConverters;

import es.ies.lazaro.db.ConnectionDB;
import es.ies.lazaro.model.Alumno;
import es.ies.lazaro.utils.Colors;

public class AlumnoDAO {
  private AlumnoDAO() {
  }

  public static void createStudent(int nia, String name, String lastName, String gender, LocalDate birthDate,
      String cycle, String course, String group) {
    String sql = "INSERT INTO alumno VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setInt(1, nia);
      ps.setString(2, name);
      ps.setString(3, lastName);
      ps.setString(4, gender);
      ps.setDate(5, java.sql.Date.valueOf(birthDate));
      ps.setString(6, cycle);
      ps.setString(7, course);
      ps.setString(8, group);

      int create = ps.executeUpdate();

      if (create == 0) {
        System.out.println(Colors.RED + "No se ha insertado el alumno " + name);
        return;
      }

      System.out.println(Colors.GREEN + "Se ha insertado el alumno " + name);
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }
  }

  public static void selectStudents() {
    try (Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM alumno");
        ResultSet rs = ps.executeQuery()) {

      if (!rs.isBeforeFirst()) {
        System.out.println(Colors.RED + "No hay alumnos para mostrar");
        return;
      }

      System.out.printf("%n%s      -MOSTRAR ALUMNOS-%n", Colors.PURPLE);
      System.out.println(Colors.WHITE + FlipTableConverters.fromResultSet(rs));
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }
  }

  public static List<Alumno> findStudents() {
    String sql = "SELECT * FROM alumno";
    List<Alumno> alumnos = new ArrayList<>();

    try (Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

      while (rs.next()) {
        alumnos.add(new Alumno(
            rs.getInt("NIA"),
            rs.getString("Nombre"),
            rs.getString("Apellidos"),
            rs.getString("Genero"),
            rs.getObject("Fecha_nacimiento", LocalDate.class),
            rs.getString("Ciclo"),
            rs.getString("Curso"),
            rs.getString("Grupo")));
      }
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }
    return alumnos;
  }

  public static void modifyStudent(String name, int nia) {
    String sql = "UPDATE alumno SET Nombre = ? WHERE NIA = ?";
    try (Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setString(1, name);
      ps.setInt(2, nia);

      ps.executeUpdate();

      // int modify = ps.executeUpdate();

      // if (modify == 0) {
      // System.out.println(Colors.RED + "No se ha encontrado al alumno con nia " +
      // nia);
      // return;
      // }

      System.out.println(Colors.GREEN + "Se ha cambia el nombre del alumno " + nia + " a " + name);
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }
  }

  public static boolean exists(int nia) {
    String sql = "SELECT * FROM alumno WHERE NIA = ?";
    try (Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setInt(1, nia);

      try (ResultSet rs = ps.executeQuery()) {
        return rs.next();
      }

    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
      return false;
    }
  }

  public static void deleteStudentByNia(int nia) {
    String sql = "DELETE FROM alumno WHERE NIA = ?";
    try (Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setInt(1, nia);

      ps.executeUpdate();

      // int delete = ps.executeUpdate();

      // if (delete == 0) {
      // System.out.println(Colors.RED + "No se ha encontrado al alumno con nia " +
      // nia);
      // return;
      // }

      System.out.println(Colors.GREEN + "Se ha eliminado el alumno con nia " + nia);
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }
  }

  public static int deleteStudentByLastName(String lastName) {
    String sql = "DELETE FROM alumno WHERE CONCAT(' ', Apellidos, ' ') LIKE CONCAT('% ', ?, ' %')";
    try (Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setString(1, lastName);

      return ps.executeUpdate();
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
      return 0;
    }
  }
}
