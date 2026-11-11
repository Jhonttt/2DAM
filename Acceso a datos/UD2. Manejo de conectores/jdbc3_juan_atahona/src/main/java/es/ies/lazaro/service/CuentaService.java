package es.ies.lazaro.service;

import java.sql.Connection;
import java.sql.SQLException;

import es.ies.lazaro.dao.CuentaDAO;
import es.ies.lazaro.dao.MovimientoDAO;
import es.ies.lazaro.db.ConnectionDB;
import es.ies.lazaro.utils.Colors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CuentaService {
  private final CuentaDAO cuentaDAO;
  private final MovimientoDAO movimientoDAO;

  public void transfer(int origin, int destination, double amount) throws SQLException {
    if (origin == destination) {
      throw new IllegalArgumentException("La cuenta de origen y destino no pueden ser la misma");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("El importe debe ser mayor que 0");
    }

    try (Connection cn = ConnectionDB.getConnection()) {
      cn.setAutoCommit(false);
      try {
        if (!cuentaDAO.debit(cn, origin, amount)) {
          throw new IllegalStateException("La cuenta de origen no existe o no tiene saldo suficiente");
        }
        if (!cuentaDAO.credit(cn, destination, amount)) {
          throw new IllegalStateException("La cuenta de destino no existe");
        }
        movimientoDAO.insert(cn, origin, destination, amount);

        cn.commit();
      } catch (SQLException | RuntimeException e) {
        try {
          cn.rollback();
        } catch (SQLException rollbackError) {
          e.addSuppressed(rollbackError);
        }
        throw e;
      }
    }
  }

  public void badTransfer(int origin, int destination, double amount) throws SQLException {
    if (origin == destination) {
      throw new IllegalArgumentException("La cuenta de origen y destino no pueden ser la misma");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("El importe debe ser mayor que 0");
    }

    try (Connection cn = ConnectionDB.getConnection()) {
      cn.setAutoCommit(false);
      try {
        if (!cuentaDAO.debit(cn, origin, amount)) {
          throw new IllegalStateException("La cuenta de origen no existe o no tiene saldo suficiente");
        }

        throw new IllegalStateException("Fallo simulado");

      } catch (SQLException | RuntimeException e) {
        try {
          cn.rollback();
        } catch (SQLException rollbackError) {
          e.addSuppressed(rollbackError);
        }
        throw e;
      }
    }
  }

  public void showBalance() throws SQLException {
    try (Connection cn = ConnectionDB.getConnection()) {
      cuentaDAO.showBalance(cn);
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }
  }

  public void insertDuplicatePK(int id, String titular, double saldo) {
    try (Connection cn = ConnectionDB.getConnection()) {
      System.out.println(Colors.WHITE + "Intentando insertar cuenta con ID: " + id);
      cuentaDAO.insert(cn, id, titular, saldo);
      System.out.println(Colors.GREEN + "Cuenta insertada correctamente.");
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error al insertar el registro");
      System.err.println(Colors.RED + "Mensaje BD : " + e.getMessage());
      System.err.println(Colors.RED + "SQLState   : " + e.getSQLState());
      System.err.println(Colors.RED + "Error Code : " + e.getErrorCode());
    }
  }

  public void generateMovimientosReport() {
    try (Connection cn = ConnectionDB.getConnection()) {
      System.out.println(Colors.ORANGE + "\n--- INFORME DE MOVIMIENTOS (Listado) ---" + Colors.RESET);
      movimientoDAO.showMovimientos(cn);

      System.out.println(Colors.ORANGE + "--- TOTAL TRANSFERIDO POR CUENTA ORIGEN ---" + Colors.RESET);
      movimientoDAO.showTotalByOrigin(cn);
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error al generar informe: " + e.getMessage());
    }
  }
}