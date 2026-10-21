import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.56.10:3306/instituto";
        String usuario = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try (Connection conexion =
                     DriverManager.getConnection(url, usuario, password)) {
            System.out.println("Conexion correcta con MySQL");
            System.out.println("Base de datos: " + conexion.getCatalog());
            try (Statement stmt = conexion.createStatement();
                 ResultSet rs = stmt.executeQuery("SHOW TABLES")) {

                System.out.println("\nTablas disponibles:");

                while (rs.next()) {
                    System.out.println("- " + rs.getString(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error de conexion:");
            e.printStackTrace();
        }
    }
}
