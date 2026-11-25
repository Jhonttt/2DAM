package es.ies.lazaro;

import java.sql.SQLException;
import java.util.List;

import es.ies.lazaro.dao.AlumnoDAO;
import es.ies.lazaro.dao.impl.AlumnoDAOImpl;
import es.ies.lazaro.model.Alumno;
import es.ies.lazaro.model.AlumnoXML;
import es.ies.lazaro.utils.Colors;

public class Ejercicio2 {
  private static final String FILA = "%-4s %-18s %-5s %-5s";

  public static void main(String[] args) {
    AlumnoDAO dao = new AlumnoDAOImpl();

    consultar(dao, "DAM", 19);
    consultar(dao, "DAM", 222);

    importar(dao, List.of(), false);

    importar(dao, List.of(
        new AlumnoXML("Álex Ruiz", 19, "DAM"),
        new AlumnoXML("Irene Gil", 22, "DAW")), false);

    List<AlumnoXML> fallido = List.of(
        new AlumnoXML("Prueba Fallo Uno", 20, "DAM"),
        new AlumnoXML("Prueba Fallo Dos", 21, "DAW"));
    comprobar(dao, fallido, "ANTES del lote fallido");
    importar(dao, fallido, true);
    comprobar(dao, fallido, "DESPUÉS del lote fallido");
  }

  private static void consultar(AlumnoDAO dao, String ciclo, int edadMin) {
    System.out.println(Colors.WHITE + "\nConsulta: ciclo=" + ciclo + ", edad mínima=" + edadMin);
    try {
      List<Alumno> alumnos = dao.alumnoCicloEdad(ciclo, edadMin);
      if (alumnos.isEmpty()) {
        error("Ningún alumno cumple con el filtro");
      } else {
        print(alumnos);
      }
    } catch (SQLException e) {
      error("Error en la base de datos: " + e.getMessage());
    }
  }

  private static void importar(AlumnoDAO dao, List<AlumnoXML> lote, boolean fallo) {
    System.out.println(Colors.WHITE + "\nImportación de " + lote.size() + " alumno(s)"
        + (fallo ? " con fallo de prueba" : ""));
    try {
      List<Integer> ids = dao.anadirAlumnos(lote, fallo);
      if (ids.isEmpty()) {
        System.out.println(Colors.WHITE + "Lote vacío: no se ha escrito nada");
        return;
      }
      System.out.println(Colors.BLUE + "Claves confirmadas:");
      ids.forEach(i -> System.out.println(Colors.WHITE + "- " + i));
    } catch (SQLException e) {
      error("Lote cancelado (rollback): " + e.getMessage());
    }
  }

  private static void comprobar(AlumnoDAO dao, List<AlumnoXML> lote, String momento) {
    System.out.println(Colors.WHITE + "\nComprobación " + momento + ":");
    for (AlumnoXML a : lote) {
      try {
        boolean existe = dao.existeNombre(a.getNombre());
        System.out.println(Colors.WHITE + "- " + a.getNombre() + ": " + (existe ? "EXISTE" : "no está en la tabla"));
      } catch (SQLException e) {
        error("Error en la base de datos: " + e.getMessage());
      }
    }
  }

  private static void error(String mensaje) {
    System.out.println(Colors.RED + mensaje);
  }

  private static void print(List<Alumno> alumnos) {
    System.out.println(Colors.BLUE);
    System.out.printf(FILA, "ID", "NOMBRE", "EDAD", "CICLO");
    System.out.println("\n" + "-".repeat(32));
    System.out.print(Colors.WHITE);
    alumnos.forEach(a -> {
      System.out.printf(FILA, a.getId(), a.getNombre(), a.getEdad(), a.getCiclo());
      System.out.println();
    });
  }
}