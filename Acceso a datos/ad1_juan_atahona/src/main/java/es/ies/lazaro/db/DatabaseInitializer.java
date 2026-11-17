package es.ies.lazaro.db;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
  public static void initialize() throws IOException, SQLException {
    try (InputStream is = DatabaseInitializer.class.getResourceAsStream("/db/schema.sql")) {
      if (is == null) {
        throw new IOException("No existe /db/schema.sql");
      }

      String script = new String(is.readAllBytes(), StandardCharsets.UTF_8);

      try (Connection cn = ConnectionDB.getServerConnectionDB();
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
