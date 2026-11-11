package es.ies.lazaro.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface CuentaDAO {
    boolean debit(Connection cn, int id, double amount) throws SQLException;
    boolean credit(Connection cn, int id, double amount) throws SQLException;
    void showBalance(Connection cn) throws SQLException;
    void insert(Connection cn, int id, String titular, double saldo) throws SQLException;
}