package es.ies.lazaro.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jakewharton.fliptables.FlipTableConverters;

import es.ies.lazaro.dao.CuentaDAO;

public class CuentaDAOImpl implements CuentaDAO {

    @Override
    public boolean debit(Connection cn, int id, double amount) throws SQLException {
        String sql = "UPDATE cuentas SET saldo = saldo - ? WHERE id = ? AND saldo >= ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, id);
            ps.setDouble(3, amount);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean credit(Connection cn, int id, double amount) throws SQLException {
        String sql = "UPDATE cuentas SET saldo = saldo + ? WHERE id = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public void showBalance(Connection cn) throws SQLException {
        String sql = "SELECT * FROM cuentas";
        try (Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            if (!rs.isBeforeFirst())
                throw new IllegalArgumentException("No hay cuentas");
            System.out.println(FlipTableConverters.fromResultSet(rs));
        }
    }

    @Override
    public void insert(Connection cn, int id, String titular, double saldo) throws SQLException {
        String sql = "INSERT INTO cuentas (id, titular, saldo) VALUES (?, ?, ?)";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, titular);
            ps.setDouble(3, saldo);
            ps.executeUpdate();
        }
    }

}