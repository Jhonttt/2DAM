package es.instituto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public final class ConexionInicial {

  private static final Dotenv env = Dotenv.load();
  private static final String URL = env.get("URL");
  private static final String USER = env.get("USER");
  private static final String PASS = env.get("PASS");

  private ConexionInicial() {
  }

  public static Connection getConnection() throws SQLException {
    Connection con = DriverManager.getConnection(URL, USER, PASS);
    System.out.println("Conexión establecida con: " + con.getCatalog());
    return con;
  }

}
