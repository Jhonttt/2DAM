package es.instituto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {
  public static List<Alumno> selectAlumnos() {
    String sql = "SELECT id, nombre, edad, ciclo FROM alumnos ORDER BY id";
    List<Alumno> alumnos = new ArrayList<>();
    try (Connection con = ConexionInicial.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

      while (rs.next()) {
        alumnos.add(new Alumno(rs.getInt("id"), rs.getString(2), rs.getInt("edad"), rs.getString("ciclo")));
      }

      return alumnos;

    } catch (Exception e) {
      System.err.println(Colors.RED + "Error de MySQL: " + e.getMessage() + Colors.RESET);
    }
    return null;
  }

  public static List<Alumno> selectAlumnosByCicle(String cicle) {
    String sql = "SELECT * FROM alumnos WHERE ciclo = ? ORDER BY nombre";
    List<Alumno> alumnos = new ArrayList<>();
    try (Connection con = ConexionInicial.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setString(1, cicle);

      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          alumnos.add(new Alumno(
              rs.getInt("id"), rs.getString("nombre"),
              rs.getInt("edad"), rs.getString("ciclo")));
        }
      }

      return alumnos;
    } catch (Exception e) {
      System.err.println(Colors.RED + "Error de MySQL: " + e.getMessage() + Colors.RESET);
    }
    return null;
  }

  public static List<Alumno> selectAlumnosByCicleAndAge(String cicle, int age) {
    String sql = "SELECT * FROM alumnos WHERE ciclo = ? AND edad >= ? ORDER BY edad";
    List<Alumno> alumnos = new ArrayList<>();
    try (Connection con = ConexionInicial.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setString(1, cicle);
      ps.setInt(2, age);

      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          alumnos.add(new Alumno(
              rs.getInt("id"), rs.getString("nombre"),
              rs.getInt("edad"), rs.getString("ciclo")));
        }
      }

      return alumnos;
    } catch (Exception e) {
      System.err.println(Colors.RED + "Error de MySQL: " + e.getMessage() + Colors.RESET);
    }
    return null;
  }

  public static void selectSummaryStudents() {
    String sql = "SELECT ciclo, COUNT(*) as total, AVG(edad) as edad_media FROM alumnos GROUP BY ciclo";
    try (Connection con = ConexionInicial.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          System.out.printf("%s | %d alumnos | edad media: %.1f%n",
              rs.getString("ciclo"),
              rs.getInt("total"),
              rs.getDouble("edad_media"));
        }
      }

      System.out.println();
    } catch (Exception e) {
      System.err.println(Colors.RED + "Error de MySQL: " + e.getMessage() + Colors.RESET);
    }
  }
}
