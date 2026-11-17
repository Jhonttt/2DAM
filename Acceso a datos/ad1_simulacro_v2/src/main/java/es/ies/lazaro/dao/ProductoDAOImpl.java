package es.ies.lazaro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.ies.lazaro.model.Producto;

public class ProductoDAOImpl implements ProductoDAO {
  private static Producto mapper(ResultSet rs) throws SQLException {
    return new Producto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
  }

  @Override
  public List<Producto> maxPrice(double precio, Connection cn) throws SQLException {
    String sql = "SELECT id, nombre, precio, stock FROM productos WHERE precio <= ?";
    try (PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setDouble(1, precio);
      return read(ps);
    }
  }

  @Override
  public boolean sell(int id, Connection cn) throws SQLException {
    String sql = "UPDATE productos SET stock = stock - 1 WHERE id = ? AND stock > 0";
    try (PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setInt(1, id);
      return ps.executeUpdate() > 0;
    }
  }

  @Override
  public List<Producto> findAll(Connection cn) throws SQLException {
    try (PreparedStatement ps = cn.prepareStatement("SELECT id, nombre, precio, stock FROM productos")) {
      return read(ps);
    }
  }

  private static List<Producto> read(PreparedStatement ps) throws SQLException {
    List<Producto> productos = new ArrayList<>();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        productos.add(mapper(rs));
      }
    }
    return productos;
  }
}