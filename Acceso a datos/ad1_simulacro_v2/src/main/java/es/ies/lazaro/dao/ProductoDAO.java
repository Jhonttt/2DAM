package es.ies.lazaro.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import es.ies.lazaro.model.Producto;

public interface ProductoDAO {
  List<Producto> maxPrice(double precio, Connection cn) throws SQLException;

  /** @return true si se vendió una unidad; false si no existe o no hay stock */
  boolean sell(int id, Connection cn) throws SQLException;

  List<Producto> findAll(Connection cn) throws SQLException;
}