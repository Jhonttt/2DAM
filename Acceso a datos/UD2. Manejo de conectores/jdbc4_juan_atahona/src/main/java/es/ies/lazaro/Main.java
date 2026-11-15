package es.ies.lazaro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.ies.lazaro.db.AlumnoMapper;
import es.ies.lazaro.db.ConnectionDB;
import es.ies.lazaro.db.DatabaseInitializer;
import es.ies.lazaro.model.Alumno;
import es.ies.lazaro.utils.Colors;

public class Main {

    private static void initialize() {
        try {
            DatabaseInitializer.initialize();
            System.out.println(Colors.GREEN + "Base de datos lista");
        } catch (SQLException | IOException e) {
            System.err.println(Colors.RED + "Error inicializando la base de datos: " + e.getMessage());
            System.exit(2);
        }
    }

    private static void alumno(Connection con, int id) {
        String sql = "SELECT id, nombre, edad, ciclo FROM alumnos WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Alumno a = AlumnoMapper.mapear(rs);
                    System.out.println(a);
                } else {
                    System.out.println("No existe ningún alumno con id " + id);
                }
            }
        } catch (SQLException e) {
            System.err.println(Colors.RED + "Error consultando: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        initialize();

         try (Connection con = ConnectionDB.getConnectionDB()) {
            alumno(con, 1);
        } catch (SQLException e) {
            System.err.println(Colors.RED + "Error de conexión: " + e.getMessage());
        }
    }
}