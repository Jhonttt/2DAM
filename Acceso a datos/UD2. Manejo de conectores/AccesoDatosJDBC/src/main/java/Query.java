import java.sql.*;

public class Query {
    public static void main(String[] args) {
        String sql = "SELECT id, nombre, edad FROM alumnos WHERE ciclo = ? AND edad >= ?";

        try (Connection conexion = ConnectionDB.getConnection();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setString(1, "DAM");
            consulta.setInt(2, 20);

            try (ResultSet rs = consulta.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + " - "
                            + rs.getString("nombre") + " - "
                            + rs.getInt("edad"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}