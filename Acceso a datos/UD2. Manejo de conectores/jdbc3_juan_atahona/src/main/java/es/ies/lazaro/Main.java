package es.ies.lazaro;

import java.io.IOException;
import java.sql.SQLException;

import es.ies.lazaro.dao.impl.CuentaDAOImpl;
import es.ies.lazaro.dao.impl.MovimientoDAOImpl;
import es.ies.lazaro.db.InitializeDB;
import es.ies.lazaro.service.CuentaService;
import es.ies.lazaro.utils.Colors;

public class Main {
    private static final CuentaService SERVICE = new CuentaService(new CuentaDAOImpl(), new MovimientoDAOImpl());

    private static void initialize() {
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

        // Ejercicio 1:
        System.out.println(Colors.BLUE + "Ejercicio 1");
        try {
            SERVICE.transfer(1, 2, 50.0);
            System.out.println(Colors.GREEN + "Transferencia realizada");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println(Colors.RED + e.getMessage());
        } catch (SQLException e) {
            System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
        }
        
        // Ejercicio 2:
        System.out.println(Colors.BLUE + "Ejercicio 2");
        try {
            System.out.println(Colors.WHITE + "Antes de la nueva transferencia: ");
            SERVICE.showBalance();
        } catch (SQLException e) {
            System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
        }

        try {
            SERVICE.badTransfer(1, 2, 50.0);
            System.out.println(Colors.GREEN + "Transferencia realizada");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println(Colors.RED + "Error con los argumentos: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
        }

        try {
            System.out.println(Colors.WHITE + "\nTras la nueva transferencia: ");
            SERVICE.showBalance();
        } catch (SQLException e) {
            System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
        }
        
        // Ejercicio 3:
        System.out.println(Colors.BLUE + "Ejercicio 3");
        SERVICE.insertDuplicatePK(1, "Carlos Gómez", 500.50);

        try {
            System.out.println(Colors.WHITE + "Tras intentar la inserción fallida: ");
            SERVICE.showBalance();
        } catch (SQLException e) {
            System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
        }

        // Ejercicio 4:
        System.out.println(Colors.BLUE + "Ejercico 4");
        
        try {
            SERVICE.transfer(1, 2, 120.50);
            System.out.println(Colors.GREEN + "Segunda transferencia realizada con éxito para el informe.");
        } catch (SQLException | IllegalArgumentException | IllegalStateException e) {
            System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
        }

        SERVICE.generateMovimientosReport();
    }
}