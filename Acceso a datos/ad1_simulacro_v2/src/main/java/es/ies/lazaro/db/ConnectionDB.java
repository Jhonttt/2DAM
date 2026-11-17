package es.ies.lazaro.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public final class ConnectionDB {
  private static final Dotenv ENV = Dotenv.configure().ignoreIfMissing().load();

  private ConnectionDB() {}

  private static String required(String key) throws SQLException {
    String value = ENV.get(key);
    if (value == null || value.isBlank()) {
      throw new SQLException("Falta la variable " + key + " en el fichero .env");
    }
    return value;
  }

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(required("DB_URL"), required("DB_USER"), ENV.get("DB_PASS", ""));
  }

  public static Connection getServerConnection() throws SQLException {
    return DriverManager.getConnection(required("DB_URL_SERVER"), required("DB_USER"), ENV.get("DB_PASS", ""));
  }
}