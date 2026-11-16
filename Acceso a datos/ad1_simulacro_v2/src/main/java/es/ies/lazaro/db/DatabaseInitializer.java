package es.ies.lazaro.db;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import es.ies.lazaro.utils.Colors;

public final class DatabaseInitializer {
  public static void initialize() throws SQLException, IOException {
    try (InputStream is = DatabaseInitializer.class.getResourceAsStream("/db/schema.sql")) {
      if (is == null) {
        System.out.println(Colors.RED + "No existe el fichero /db/schema.sql");
        System.exit(1);
      }

      String script = new String(is.readAllBytes(), StandardCharsets.UTF_8);

      try (Connection cn = ConnectionDB.getServerConnection();
          Statement st = cn.createStatement()) {
        for (String statement : script.split(";")) {
          if (!statement.isBlank()) {
            st.execute(statement.trim());
          }
        }
      }
    }
  }
}
