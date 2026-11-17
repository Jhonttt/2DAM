package es.ies.lazaro;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import es.ies.lazaro.dao.AlumnoDAO;
import es.ies.lazaro.dao.impl.AlumnoDAOImpl;
import es.ies.lazaro.db.DatabaseInitializer;
import es.ies.lazaro.model.Alumno;
import es.ies.lazaro.utils.Colors;
import es.ies.lazaro.utils.Entrada;

public class Main {
  private static final String FILA = "%-5s %-30s %-6s %-6s%n";

  public static void main(String[] args) {
    try {
      DatabaseInitializer.initialize();
      log(Colors.GREEN, "Base de datos lista");
    } catch (IOException | SQLException e) {
      log(Colors.RED, "Error inicializando la base de datos: " + e.getMessage());
      System.exit(2);
    }

    AlumnoDAO dao = new AlumnoDAOImpl();
    try {
      menu(dao);
    } catch (IOException e) {
      log(Colors.ORANGE, "Fin de la entrada. Saliendo...");
    }
  }

  private static void menu(AlumnoDAO dao) throws IOException {
    boolean salir = false;
    while (!salir) {
      mostrarMenu();
      int opcion = Entrada.leerEntero("Elige una opción: ");
      try {
        switch (opcion) {
          case 1 -> listar(dao);
          case 2 -> buscar(dao);
          case 3 -> alta(dao);
          case 4 -> modificarCiclo(dao);
          case 5 -> eliminar(dao);
          case 0 -> salir = true;
          default -> log(Colors.RED, "Opción no válida");
        }
      } catch (SQLException e) {
        log(Colors.RED, "No se pudo completar la operación con la base de datos");
        System.err.println("Detalle técnico: " + e.getMessage());
      }
    }
    log(Colors.GREEN, "¡Hasta pronto!");
  }

  private static void mostrarMenu() {
    System.out.println(Colors.PURPLE + "\n=== GESTOR ACADÉMICO ===" + Colors.RESET);
    System.out.println("1. Listar alumnos");
    System.out.println("2. Buscar por ID");
    System.out.println("3. Dar de alta");
    System.out.println("4. Modificar ciclo");
    System.out.println("5. Eliminar");
    System.out.println("0. Salir");
  }

  private static void listar(AlumnoDAO dao) throws SQLException {
    List<Alumno> alumnos = dao.listar();
    if (alumnos.isEmpty()) {
      log(Colors.ORANGE, "No hay alumnos registrados");
      return;
    }
    mostrar(alumnos);
  }

  private static void buscar(AlumnoDAO dao) throws IOException, SQLException {
    int id = Entrada.leerEntero("ID del alumno: ");
    Alumno a = dao.buscarPorId(id);
    if (a == null) {
      log(Colors.ORANGE, "No existe ningún alumno con id " + id);
      return;
    }
    mostrar(List.of(a));
  }

  private static void alta(AlumnoDAO dao) throws IOException, SQLException {
    String nombre = Entrada.leerNombre("Nombre: ");
    int edad = Entrada.leerEdad("Edad: ");
    String ciclo = Entrada.leerCiclo("Ciclo");

    int id = dao.insertar(new Alumno(0, nombre, edad, ciclo));
    log(Colors.GREEN, "Alumno dado de alta con id " + id);
  }

  private static void modificarCiclo(AlumnoDAO dao) throws IOException, SQLException {
    int id = Entrada.leerEntero("ID del alumno: ");
    Alumno a = dao.buscarPorId(id);
    if (a == null) {
      log(Colors.ORANGE, "No existe ningún alumno con id " + id);
      return;
    }

    a.setCiclo(Entrada.leerCiclo("Nuevo ciclo"));
    if (dao.actualizar(a)) {
      log(Colors.GREEN, "Ciclo modificado correctamente");
    } else {
      log(Colors.ORANGE, "El alumno ya no existe");
    }
  }

  private static void eliminar(AlumnoDAO dao) throws IOException, SQLException {
    int id = Entrada.leerEntero("ID del alumno a eliminar: ");
    Alumno a = dao.buscarPorId(id);
    if (a == null) {
      log(Colors.ORANGE, "No existe ningún alumno con id " + id);
      return;
    }

    if (!Entrada.confirmar("¿Eliminar a " + a.getNombre() + " (id " + a.getId() + ")?")) {
      log(Colors.ORANGE, "Operación cancelada");
      return;
    }

    if (dao.eliminar(id)) {
      log(Colors.GREEN, "Alumno eliminado");
    } else {
      log(Colors.ORANGE, "El alumno ya no existe");
    }
  }

  private static void mostrar(List<Alumno> alumnos) {
    System.out.print(Colors.WHITE);
    System.out.printf(FILA, "ID", "NOMBRE", "EDAD", "CICLO");
    System.out.println("-".repeat(50));
    for (Alumno a : alumnos) {
      System.out.printf(FILA, a.getId(), a.getNombre(), a.getEdad(), a.getCiclo());
    }
    System.out.print(Colors.RESET);
  }

  private static void log(String color, String mensaje) {
    System.out.println(color + mensaje + Colors.RESET);
  }
}