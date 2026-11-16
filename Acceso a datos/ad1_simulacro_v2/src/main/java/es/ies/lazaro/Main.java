package es.ies.lazaro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import es.ies.lazaro.classes.ResumenNotas;
import es.ies.lazaro.dao.ProductoDAO;
import es.ies.lazaro.dao.ProductoDAOImpl;
import es.ies.lazaro.db.ConnectionDB;
import es.ies.lazaro.db.DatabaseInitializer;
import es.ies.lazaro.model.Producto;
import es.ies.lazaro.utils.Colors;

public class Main {
    private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    private static void initialize() {
        try {
            DatabaseInitializer.initialize();
            System.out.println(Colors.GREEN + "Base de datos lista");
        } catch (IOException | SQLException e) {
            System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Ejercicio 1
        try {
            ResumenNotas.main();
        } catch (IOException e) {
            System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage());
        }

        // Ejercicio 2
        initialize();
        ProductoDAO dao = new ProductoDAOImpl();
        while (true) {
            try {
                System.out.print(
                        Colors.BLUE + "Introduce el precio máximo para buscar las productos inferiores: "
                                + Colors.WHITE);
                String precioMaxString = BR.readLine();

                if (precioMaxString == null || precioMaxString.isBlank()) {
                    System.out.println(Colors.RED + "El precio máximo no puede estar vacío");
                    continue;
                }

                if (!precioMaxString.matches("\\d+(.\\d+)*")) {
                    System.out.println(Colors.RED + "Introduce un precio máximo válido");
                    continue;
                }

                try (Connection cn = ConnectionDB.getConnection()) {
                    List<Producto> productos = dao.maxPrice(Double.parseDouble(precioMaxString), cn);
                    if (productos.isEmpty()) {
                        System.err.println(Colors.ORANGE + "No existe ninguna coincidencia");
                        break;
                    }
                    productos.forEach(System.out::println);
                    break;
                } catch (SQLException e) {
                    System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
                    System.exit(1);
                }
            } catch (IOException e) {
                System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print(
                        Colors.BLUE + "Introduce el ID de cuyo producto quieras vender: "
                                + Colors.WHITE);
                String idString = BR.readLine();

                if (idString == null || idString.isBlank()) {
                    System.out.println(Colors.RED + "El id no puede estar vacío");
                    continue;
                }

                if (!idString.matches("\\d+")) {
                    System.out.println(Colors.RED + "Introduce un id válido");
                    continue;
                }

                try (Connection cn = ConnectionDB.getConnection()) {
                    dao.show(cn);
                    dao.sell(Integer.parseInt(idString), cn);
                    dao.show(cn);
                    break;
                } catch (SQLException e) {
                    System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
                    System.exit(1);
                }
            } catch (IOException e) {
                System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage());
            }
        }

    }
}