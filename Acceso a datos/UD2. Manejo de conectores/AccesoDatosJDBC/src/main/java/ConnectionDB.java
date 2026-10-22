import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionDB {

    private static final Dotenv env = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                env.get("DB_URL"),
                env.get("DB_USER"),
                env.get("DB_PASSWORD"));
    }
}