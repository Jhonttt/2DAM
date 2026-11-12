package es.ies.lazaro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import es.ies.lazaro.dao.ProductoDAO;
import es.ies.lazaro.dao.impl.ProductoDAOImpl;
import es.ies.lazaro.db.ConnectionDB;
import es.ies.lazaro.db.DatabaseInitializer;
import es.ies.lazaro.utils.Colors;

public class GestionTienda {
  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

  private static void initialize() {
    try {
      DatabaseInitializer.initialize();
      System.out.println(Colors.GREEN + "Base de datos lista");
    } catch (SQLException | IOException e) {
      System.err.println(Colors.RED + "Error inicializando la base de datos: " + e.getMessage());
      System.exit(2);
    }
  }

  public static void main() {
    initialize();
    ProductoDAO DAO = new ProductoDAOImpl();
    double max = 0.0;
    int id = 0;
    try (Connection cn = ConnectionDB.getConnection()) {
      while (true) {
        System.out.print(Colors.BLUE + "Introduce el número máximo para buscar los inferiores: " + Colors.WHITE);
        String inputMax = BR.readLine();

        if (inputMax == null || !inputMax.matches("\\d+(.\\d+)*")) {
          System.out.println(Colors.RED + "Introduce un número válido");
          continue;
        }

        max = Double.parseDouble(inputMax);

        if (max <= 0) {
          System.out.println(Colors.RED + "Introduce un número mayor a 0");
          continue;
        }

        break;
      }

      DAO.maxPrice(cn, max);

    } catch (IOException e) {
      System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage());
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }
    
    try (Connection cn = ConnectionDB.getConnection()) {
      DAO.showProducts(cn);
      while (true) {
        System.out.print(Colors.BLUE + "Introduce un id para venderlo: " + Colors.WHITE);
        String inputId = BR.readLine();

        if (inputId == null || !inputId.matches("\\d+")) {
          System.out.println(Colors.RED + "Introduce un número válido");
          continue;
        }

        id = Integer.parseInt(inputId);

        if (id <= 0) {
          System.out.println(Colors.RED + "Introduce un número mayor a 0");
          continue;
        }

        break;
      }
      DAO.sellProduct(cn, id);
      DAO.showProducts(cn);
    } catch (IOException e) {
      System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage());
    } catch (SQLException e) {
      System.err.println(Colors.RED + "Error en la base de datos: " + e.getMessage());
    }

  }

}