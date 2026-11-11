package es.ies.lazaro.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface MovimientoDAO {
  void insert(Connection cn, int origin, int destination, double amount) throws SQLException;
  void showMovimientos(Connection cn) throws SQLException;
  void showTotalByOrigin(Connection cn) throws SQLException;
}