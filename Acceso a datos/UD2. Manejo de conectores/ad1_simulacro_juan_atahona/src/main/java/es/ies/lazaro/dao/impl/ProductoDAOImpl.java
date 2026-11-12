package es.ies.lazaro.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jakewharton.fliptables.FlipTableConverters;

import es.ies.lazaro.dao.ProductoDAO;
import es.ies.lazaro.utils.Colors;

public class ProductoDAOImpl implements ProductoDAO {

  @Override
  public void maxPrice(Connection cn, double max) {
    String sql = "SELECT id, nombre, precio, stock FROM productos WHERE precio <= ?";
    try (PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setDouble(1, max);

      try (ResultSet rs = ps.executeQuery()) {
        if (!rs.isBeforeFirst()) {
          System.out.println(Colors.RED + "No hay productos para mostrar");
          return;
        }

        System.out.println(Colors.WHITE + FlipTableConverters.fromResultSet(rs));
      }

    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }
  }

  @Override
  public void sellProduct(Connection cn, int id) throws SQLException {
    String sql = "UPDATE productos SET stock = stock - ? WHERE id = ? AND stock > 0";
    try (PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setInt(1, 1);
      ps.setInt(2, id);

      if (ps.executeUpdate() == 0) {
        System.out.println(Colors.RED + "No se ha actualizado ningún registro");
        return;
      }
      System.out.println(Colors.GREEN + "Se han actualizado los registros");

    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }
  }

  // @Override
  // public int showStock(Connection cn, int id) throws SQLException {
  //   String sql = "SELECT stock FROM productos WHERE id = ? AND stock > 0";
  //   try (PreparedStatement ps = cn.prepareStatement(sql)) {
  //     ps.setInt(1, id);

  //     try (ResultSet rs = ps.executeQuery()) {
  //       rs.next();
  //       return rs.getInt(1);
  //     }

  //   } catch (SQLException e) {
  //     System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
  //     return -1;
  //   }
  // }

  @Override
  public void showProducts(Connection cn) throws SQLException {
    String sql = "SELECT * FROM productos";
    try (PreparedStatement ps = cn.prepareStatement(sql)) {
      try (ResultSet rs = ps.executeQuery()) {
        if (!rs.isBeforeFirst()) {
          System.out.println(Colors.RED + "No hay productos para mostrar");
          return;
        }

        System.out.println(Colors.WHITE + FlipTableConverters.fromResultSet(rs));
      }

    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }
  }

}
