package es.ies.lazaro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jakewharton.fliptables.FlipTableConverters;

import es.ies.lazaro.model.Producto;
import es.ies.lazaro.utils.Colors;

public class ProductoDAOImpl implements ProductoDAO {
  private static Producto mapper(ResultSet rs) throws SQLException {
    return new Producto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
  }

  @Override
  public List<Producto> maxPrice(double precio, Connection cn) throws SQLException {
    String sql = "SELECT id, nombre, precio, stock FROM productos WHERE precio <= ? AND precio > 0";
    try (PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setDouble(1, precio);

      List<Producto> productos = new ArrayList<>();
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          productos.add(mapper(rs));
        }
      }

      return productos;
    }
  }

  @Override
  public void sell(int id, Connection cn) throws SQLException {
    String sql = "UPDATE productos SET stock = stock - 1 WHERE id = ? AND stock > 0";
    try (PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setInt(1, id);

      boolean updated = ps.executeUpdate() > 0;

      if (!updated) {
        System.err.println(Colors.RED + "No se ha podido hacer la venta");
        return;
      }

      System.out.println(Colors.GREEN + "Venta realizada correctamente");
    }
  }

  @Override
  public void show(Connection cn) throws SQLException {
    String sql = "SELECT * FROM productos";
    try (PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql)) {

      if (rs.isAfterLast()) {
        System.out.println(Colors.RED + "No hay na");
      }

      System.out.println(Colors.WHITE + FlipTableConverters.fromResultSet(rs));
    }
  }

}
