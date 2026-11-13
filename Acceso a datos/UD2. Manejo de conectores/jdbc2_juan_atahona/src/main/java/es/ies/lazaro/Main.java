package es.ies.lazaro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import es.ies.lazaro.dao.AlumnoDAO;
import es.ies.lazaro.db.DatabaseInitializer;
import es.ies.lazaro.utils.Colors;

public class Main {
    private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    private static final String LETTERS = "\\p{L}+( \\p{L}+)*";
    private static final String AGE = "[1-9]\\d{0,8}";
    private static final String NUMBER = "\\d+";
    private static final String CANCEL_WORD = "cancelar";

    private static void initialize() {
        try {
            DatabaseInitializer.initialize();
            System.out.println(Colors.GREEN + "  ---BASE DE DATOS LISTA---");
        } catch (SQLException | IOException e) {
            System.err.println(Colors.RED + "Error inicializando la base de datos: " + e.getMessage());
            System.exit(2);
        }
    }

    private static int createStudent() {
        String name, cycle;
        int age;

        try {
            while (true) {
                System.out.print(Colors.BLUE + "Introduce el nombre: " + Colors.WHITE);
                name = BR.readLine();

                if (name == null) {
                    System.err.println(Colors.RED + "\nSaliendo...");
                    System.exit(0);
                }

                if (!name.matches(LETTERS) || name.length() > 60) {
                    System.out.println(Colors.RED
                            + "El nombre no puede estar vacío, solo se permiten letras y un único espacio entre palabras y no puede ser superior a 60 caracteres\n");
                    continue;
                }

                break;
            }

            while (true) {
                System.out.print(Colors.BLUE + "Introduce la edad: " + Colors.WHITE);
                String validateAge = BR.readLine();

                if (validateAge == null) {
                    System.err.println(Colors.RED + "\nSaliendo...");
                    System.exit(0);
                }

                if (!validateAge.matches(AGE)) {
                    System.out.println(
                            Colors.RED + "La edad tiene que ser un número positivo (mayor que 0)\n");
                    continue;
                }

                age = Integer.parseInt(validateAge);
                break;
            }

            while (true) {
                System.out.print(Colors.BLUE + "Introduce el ciclo (DAM/DAW): " + Colors.WHITE);
                cycle = BR.readLine();

                if (cycle == null) {
                    System.err.println(Colors.RED + "\nSaliendo...");
                    System.exit(0);
                }

                if (!cycle.equalsIgnoreCase("DAM") && !cycle.equalsIgnoreCase("DAW")) {
                    System.out.println(Colors.RED
                            + "El ciclo no puede estar vacío, solo se permite 'DAM' o 'DAW'\n");
                    continue;
                }

                break;
            }

            return AlumnoDAO.createStudent(name, age, cycle);
        } catch (IOException e) {
            System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage());
            return -1;
        }
    }

    private static void findStudents() {
        String cycle;
        int age;

        try {
            while (true) {
                System.out.print(Colors.BLUE + "Introduce la edad: " + Colors.WHITE);
                String validateAge = BR.readLine();

                if (validateAge == null) {
                    System.err.println(Colors.RED + "\nSaliendo...");
                    System.exit(0);
                }

                if (!validateAge.matches(AGE)) {
                    System.out.println(
                            Colors.RED + "La edad tiene que ser un número positivo (mayor que 0)\n");
                    continue;
                }

                age = Integer.parseInt(validateAge);
                break;
            }

            while (true) {
                System.out.print(Colors.BLUE + "Introduce el ciclo (DAM/DAW): " + Colors.WHITE);
                cycle = BR.readLine();

                if (cycle == null) {
                    System.err.println(Colors.RED + "\nSaliendo...");
                    System.exit(0);
                }

                if (!cycle.equalsIgnoreCase("DAM") && !cycle.equalsIgnoreCase("DAW")) {
                    System.out.println(Colors.RED
                            + "El ciclo no puede estar vacío, solo se permite 'DAM' o 'DAW'\n");
                    continue;
                }

                break;
            }

            AlumnoDAO.findStudents(cycle, age);
        } catch (IOException e) {
            System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage());
        }
    }

    private static void updateStudentsCycle() {
        String cycle;
        int id;

        try {
            while (true) {
                System.out.print(Colors.BLUE + "Introduce el id: " + Colors.WHITE);
                String validateId = BR.readLine();

                if (validateId == null) {
                    System.err.println(Colors.RED + "\nSaliendo...");
                    System.exit(0);
                }

                if (!validateId.matches(NUMBER)) {
                    System.out.println(
                            Colors.RED + "El id tiene que ser un número\n");
                    continue;
                }

                try {
                    id = Integer.parseInt(validateId);
                } catch (NumberFormatException e) {
                    System.out.println(
                            Colors.RED + "El id tiene que ser un número válido\n");
                    continue;
                }

                break;
            }

            while (true) {
                System.out.print(Colors.BLUE + "Introduce el ciclo (DAM/DAW): " + Colors.WHITE);
                cycle = BR.readLine();

                if (cycle == null) {
                    System.err.println(Colors.RED + "\nSaliendo...");
                    System.exit(0);
                }

                if (!cycle.equalsIgnoreCase("DAM") && !cycle.equalsIgnoreCase("DAW")) {
                    System.out.println(Colors.RED
                            + "El ciclo no puede estar vacío, solo se permite 'DAM' o 'DAW'\n");
                    continue;
                }

                break;
            }

            AlumnoDAO.updateStudentsCycle(cycle, id);
        } catch (IOException e) {
            System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage());
        }
    }

    private static void deleteStudents() {
        int id;

        try {
            while (true) {
                while (true) {
                    System.out.print(Colors.BLUE + "Introduce el id: " + Colors.WHITE);
                    String validateId = BR.readLine();

                    if (validateId == null) {
                        System.err.println(Colors.RED + "\nSaliendo...");
                        System.exit(0);
                    }

                    if (validateId.equalsIgnoreCase(CANCEL_WORD)) {
                        System.err.println(Colors.RED + "Cancelando...");
                        return;
                    }

                    if (!validateId.matches(NUMBER)) {
                        System.out.println(
                                Colors.RED + "El id tiene que ser un número\n");
                        continue;
                    }

                    try {
                        id = Integer.parseInt(validateId);
                    } catch (NumberFormatException e) {
                        System.out.println(
                                Colors.RED + "El id tiene que ser un número válido\n");
                        continue;
                    }

                    break;
                }

                int rowsDeleted = AlumnoDAO.countStudentsById(id);
                while (rowsDeleted > 0) {
                    System.out
                            .print(Colors.BLUE + "¿Desea eliminar "
                                    + (rowsDeleted > 1
                                            ? "las " + Colors.RED + rowsDeleted + Colors.BLUE + " coincidencias"
                                            : "la" + Colors.RED + " coincidencia" + Colors.BLUE)
                                    + " (N/y)? (" + Colors.RED + "\"cancelar\"" + Colors.BLUE + " para salir): "
                                    + Colors.WHITE);
                    String confirmDelete = BR.readLine();

                    if (confirmDelete == null) {
                        System.out.println(Colors.RED + "\nSaliendo...");
                        System.exit(0);
                    }

                    if (confirmDelete.equalsIgnoreCase(CANCEL_WORD)) {
                        System.err.println(Colors.RED + "Cancelando...");
                        return;
                    }

                    if (confirmDelete.equalsIgnoreCase("y") || confirmDelete.equalsIgnoreCase("yes")
                            || confirmDelete.equalsIgnoreCase("s") || confirmDelete.equalsIgnoreCase("si")) {
                        AlumnoDAO.deleteStudents(id);
                        return;
                    }

                    if (confirmDelete.equalsIgnoreCase("n") || confirmDelete.equalsIgnoreCase("no")) {
                        System.out
                                .println(Colors.RED + "No se " + (rowsDeleted == 1 ? "ha eliminado " : "han eliminado ")
                                        + Colors.PURPLE + rowsDeleted + Colors.RED
                                        + (rowsDeleted == 1 ? " alumno" : " alumnos"));
                        return;
                    }
                }

                System.out.println(Colors.RED + "No existe un alumno con el id " + id
                        + " intentalo de nuevo (" + Colors.PURPLE + "\"cancelar\"" + Colors.RED + " para salir)\n");
            }
        } catch (IOException e) {
            System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage());
        }
    }

    private static void menuModifyDelete() {
        while (true) {
            try {
                System.out.printf(
                        "%n%s\t--OPCIONES--%n%s1.- Actualizar ciclo%n2.- Eliminar alumno%n%s0.- Salir%n%s Opción: ",
                        Colors.PURPLE, Colors.BLUE, Colors.RED, Colors.WHITE);

                String option = BR.readLine();

                if (option == null) {
                    System.out.println(Colors.RED + "\nSaliendo...");
                    break;
                }

                if (!option.trim().matches("[0-2]")) {
                    throw new NumberFormatException("Debes introducir un número del 0 al 2");
                }

                int op = Integer.parseInt(option.trim());

                if (op == 0) {
                    System.out.println(Colors.RED + "\nSaliendo...");
                    break;
                }

                switch (option) {
                    case "1" -> updateStudentsCycle();
                    case "2" -> deleteStudents();
                    default -> System.err.println(Colors.RED + "Opción no disponible todavía.");
                }
            } catch (IOException e) {
                System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println(Colors.RED + "Error de formato: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println(Colors.RED + "Argumento no válido: " + e.getMessage());
            }
        }
    }

    private static void registerAndShowCard() {
        int id = createStudent();

        if (id == -1) {
            System.out.println(Colors.RED + "No se ha obtenido ningún ID, no se muestra la ficha");
            return;
        }

        AlumnoDAO.findStudentById(id);
    }

    public static void main(String[] args) {
        initialize();
        System.out.println(Colors.ORANGE + "EJERCICIO 1----------------");
        createStudent();
        System.out.println(Colors.ORANGE + "EJERCICIO 2----------------");
        findStudents();
        System.out.println(Colors.ORANGE + "EJERCICIO 3----------------");
        menuModifyDelete();
        System.out.println(Colors.ORANGE + "EJERCICIO 4----------------");
        registerAndShowCard();
        /**
         * ? La diferencia entre filas afectadas e ID generado:
         ** Las filas afectadas son como su nombre lo indica las filas/registros
         * que se modifican al ejecutar una operación de modificación (INSERT, UPDATE o
         * DELETE). Un SELECT no tiene filas afectadas: devuelve un conjunto de
         * resultados (ResultSet) que se recorre para leer los registros.
         ** En cuanto al ID generado es el ID que se genera de forma automatica cuando se
         * especifica en la creación de dicho ID en mysql, poniendo la palabra
         * reservada/clave AUTO_INCREMENT, dicho ID no habría necesidad por tanto de
         * generarlo porque como su nombre lo indica se genera solo y suele ser
         * ascendente al número en el que se encuentra, lo malo es que si borras un
         * registro (cosa que no se hace, lo que se hace es poner un campo de eliminado
         * en true o algo por el estilo) quedaran huecos entre medio de la base de
         * datos.
         * ? ¿Qué pasa si no se obtiene la clave al crear el registro?:
         ** Simplemente que a la hora de crear dicho registro en la base de datos no
         * sabríamos cual es su id para poder modificarlo de una forma segura pues es
         * primary key lo que quiere decir que no se puede repetir a diferencia de los
         * otros campos, ¿Pero no se puede filtrar por los otros campos? si se puede
         * filtrar por los otros campos y de hecho cuantos más argumentos/parametros
         * tomes en consideración para filtrar más improbable que te devuelva más de un
         * registro, pero ahí esta la clave, nunca es 0 la probabilidad además que si
         * son muchos campos pierde rendimiento pues es más eficaz filtrar por un campo
         * que por más de uno, por ello se recoge el id y se trabaja con el.
         */

    }
}