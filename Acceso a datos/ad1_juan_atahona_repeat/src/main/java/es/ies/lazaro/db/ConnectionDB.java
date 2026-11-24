package es.ies.lazaro.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public final class ConnectionDB {

  private static final Dotenv ENV = Dotenv.configure().ignoreIfMissing().load();

  private ConnectionDB() {}
  
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(ENV.get("URL"), ENV.get("USER"), ENV.get("PASS"));
  }
}
