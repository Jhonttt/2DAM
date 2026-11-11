package es.ies.lazaro.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jakewharton.fliptables.FlipTableConverters;

import es.ies.lazaro.dao.MovimientoDAO;

public class MovimientoDAOImpl implements MovimientoDAO {

    @Override
    public void insert(Connection cn, int origin, int destination, double amount) throws SQLException {
        String sql = "INSERT INTO movimientos (origen, destino, importe) VALUES (?, ?, ?)";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, origin);
            ps.setInt(2, destination);
            ps.setDouble(3, amount);
            ps.executeUpdate();
        }
    }

    @Override
    public void showMovimientos(Connection cn) throws SQLException {
        String sql = "SELECT origen, destino, CAST(importe AS DECIMAL(10,2)) AS importe FROM movimientos ORDER BY origen ASC, id ASC";
        try (Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            if (!rs.isBeforeFirst()) {
                System.out.println("No hay movimientos registrados (Tabla vacía).");
            } else {
                System.out.println(FlipTableConverters.fromResultSet(rs));
            }
        }
    }

    @Override
    public void showTotalByOrigin(Connection cn) throws SQLException {
        String sql = "SELECT origen, CAST(SUM(importe) AS DECIMAL(10,2)) AS total_transferido FROM movimientos GROUP BY origen";
        try (Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            if (!rs.isBeforeFirst()) {
                System.out.println("No hay totales para mostrar (Tabla vacía).");
            } else {
                System.out.println(FlipTableConverters.fromResultSet(rs));
            }
        }
    }
}