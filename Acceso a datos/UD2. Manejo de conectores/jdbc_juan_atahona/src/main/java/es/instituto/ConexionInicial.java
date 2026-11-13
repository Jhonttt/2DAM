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

  // En caso de que la contraseña o el nombre de la base de datos es incorrecta se lanzaría el SQLException que se propaga y se captura donde se use, y ahí se hace un try catch donde se captura el SQLException o el Exception general y se pone un mensaje diciendo que es lo que fallo, donde ahí puedes ponerlo como quieras, yo suelo poner el e.getMessage()
  public static Connection getConnection() throws SQLException {
    Connection con = DriverManager.getConnection(URL, USER, PASS);
    System.out.println("Conexión establecida con: " + con.getCatalog());
    return con;
  }

}
