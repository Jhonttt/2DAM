package es.ies.lazaro.db;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize() throws SQLException, IOException {
        try (InputStream in = DatabaseInitializer.class.getResourceAsStream("/db/schema.sql")) {
            if (in == null) {
                throw new IOException("No se encuentra /db/schema.sql");
            }
            String script = new String(in.readAllBytes(), StandardCharsets.UTF_8);

            try (Connection con = ConnectionDB.getServerConnection();
                    Statement st = con.createStatement()) {
                for (String statement : script.split(";")) {
                    if (!statement.isBlank()) {
                        st.execute(statement.trim());
                    }
                }
            }
        }
    }
}