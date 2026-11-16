package es.ies.lazaro.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import es.ies.lazaro.model.Producto;

public interface ProductoDAO {
  List<Producto> maxPrice(double precio, Connection cn) throws SQLException;
  void sell(int id, Connection cn) throws SQLException;
  void show(Connection cn) throws SQLException;
}
