package es.ies.lazaro.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public final class ConnectionDB {
  private static final Dotenv env = Dotenv.configure().ignoreIfMissing().load();

  private ConnectionDB() {
  }

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(env.get("URL"), env.get("USER"), env.get("PASS"));
  }

  public static Connection getServerConnection() throws SQLException {
    return DriverManager.getConnection(env.get("SERVER_URL"), env.get("USER"), env.get("PASS"));
  }

}
