package es.ies.lazaro.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ProductoDAO {
  void maxPrice(Connection cn, double max) throws SQLException;
  void sellProduct(Connection cn, int id) throws SQLException;
  // int showStock(Connection cn, int id) throws SQLException;
  void showProducts(Connection cn) throws SQLException;
}
