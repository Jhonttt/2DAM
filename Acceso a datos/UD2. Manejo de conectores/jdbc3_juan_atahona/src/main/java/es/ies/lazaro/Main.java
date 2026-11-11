package es.ies.lazaro;

import java.io.IOException;
import java.sql.SQLException;

import es.ies.lazaro.db.InitializeDB;
import es.ies.lazaro.utils.Colors;

public class Main {
    private void initialize() {
        try {
            InitializeDB.initialize();
            System.out.println(Colors.GREEN + "Base de datos cargada");
        } catch (IOException | SQLException e) {
            System.err.println(Colors.RED + "Error al inicializar la base de datos: " + e.getMessage());
            System.exit(2);
        }
    }

    public static void main(String[] args) {
        initialize();
    }
}