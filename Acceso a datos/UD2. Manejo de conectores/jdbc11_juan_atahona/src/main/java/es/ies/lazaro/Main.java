package es.ies.lazaro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.function.Function;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import es.ies.lazaro.dao.AlumnoDAO;
import es.ies.lazaro.db.DatabaseInitializer;
import es.ies.lazaro.model.Alumno;
import es.ies.lazaro.utils.Colors;

public class Main {
    private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    private static final String CANCEL_WORD = "cancelar";
    private static final String LETTERS = "\\p{L}+( \\p{L}+)*";
    private static final String WORDS = "[\\p{L}\\d]+( [\\p{L}\\d]+)*";
    private static final Path STUDENTS_CSV = Path.of("students.csv");
    private static final Path STUDENTS_JSON = Path.of("students.json");

    private static class CancelledException extends RuntimeException {
    }

    private static void initialize() {
        try {
            DatabaseInitializer.initialize();
            System.out.println(Colors.GREEN + "  ---BASE DE DATOS LISTA---" + Colors.RESET);
        } catch (SQLException | IOException e) {
            System.err.println(Colors.RED + "Error inicializando la base de datos: " + e.getMessage() + Colors.RESET);
            System.exit(2);
        }
    }

    private static <T> T ask(String label, Function<String, T> parser) throws IOException {
        while (true) {
            System.out.print(Colors.BLUE + label + ": " + Colors.WHITE);
            String input = BR.readLine();
            System.out.print(Colors.RESET);

            if (input == null || input.trim().equalsIgnoreCase(CANCEL_WORD)) {
                throw new CancelledException();
            }

            try {
                return parser.apply(input.trim());
            } catch (IllegalArgumentException e) {
                System.err.println(Colors.RED + "Argumento no válido: " + e.getMessage() + "\n" + Colors.RESET);
            }
        }
    }

    private static Function<String, String> validated(String field, int max, String regex, String rule) {
        return s -> {
            if (s.length() > max) {
                throw new IllegalArgumentException(field + ": máximo " + max + " caracteres");
            }
            if (!s.matches(regex)) {
                throw new IllegalArgumentException(field + ": " + rule);
            }
            return s;
        };
    }

    private static Integer parseNia(String s) {
        if (!s.matches("\\d{1,9}")) {
            throw new IllegalArgumentException("NIA: debe ser un número de hasta 9 dígitos");
        }
        return Integer.valueOf(s);
    }

    private static LocalDate parseDate(String s) {
        LocalDate date;
        try {
            date = LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha de nacimiento: formato aaaa-mm-dd");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha de nacimiento: no puede ser futura");
        }
        return date;
    }

    private static void addStudent() throws IOException {
        System.out.printf("%n%s-INSERTAR (\"%s\" para cancelar)-%n", Colors.PURPLE, CANCEL_WORD);
        int nia;
        String onlyLetters = "solo se permiten letras y un único espacio entre palabras";
        String lettersAndDigits = "solo se permiten letras, números y un único espacio entre palabras";

        try {
            while (true) {
                nia = ask("NIA", Main::parseNia);
                if (!AlumnoDAO.exists(nia))
                    break;
                System.out.println(Colors.RED + "Ese NIA ya existe, intentalo de nuevo\n");
            }

            String name = ask("Nombre", validated("Nombre", 50, LETTERS, onlyLetters));
            String lastName = ask("Apellidos", validated("Apellidos", 100, LETTERS, onlyLetters));
            String gender = ask("Género", validated("Género", 20, LETTERS, onlyLetters));
            LocalDate birthDate = ask("Fecha de nacimiento (aaaa-mm-dd)", Main::parseDate);
            String cycle = ask("Ciclo", validated("Ciclo", 50, LETTERS, onlyLetters));
            String course = ask("Curso", validated("Curso", 20, WORDS, lettersAndDigits));
            String group = ask("Grupo", validated("Grupo", 20, WORDS, lettersAndDigits));

            AlumnoDAO.createStudent(nia, name, lastName, gender, birthDate, cycle, course, group);
        } catch (CancelledException e) {
            System.out.println(Colors.RED + "Operación cancelada");
        }
    }

    private static void saveStudentsCsv() {
        List<Alumno> students = AlumnoDAO.findStudents();
        if (students.isEmpty()) {
            System.out.println(Colors.RED + "No hay alumnos para guardar vía csv");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(STUDENTS_CSV.toFile(), StandardCharsets.UTF_8))) {

            bw.write("nia;name;lastName;gender;birthDate;cycle;course;group");

            for (Alumno student : students) {
                bw.newLine();
                bw.write(String.join(";",
                        String.valueOf(student.getNia()),
                        student.getName(),
                        student.getLastName(),
                        student.getGender(),
                        String.valueOf(student.getBirthDate()),
                        student.getCycle(),
                        student.getCourse(),
                        student.getGroup()));
            }

            System.out.printf("%n%s      -GUARDAR EN SVG-%n", Colors.PURPLE);
            System.out.println(Colors.GREEN + "Los alumnos se han guardado en students.svg");

        } catch (IOException e) {
            System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage() + Colors.RESET);

        }
    }

    private static void loadStudentsCsv() {
        if (!Files.exists(STUDENTS_CSV)) {
            System.out.println(Colors.RED + "No existe el fichero CSV de alumnos");
            return;
        }

        try (BufferedReader br = new BufferedReader(
                new FileReader(STUDENTS_CSV.toFile(), StandardCharsets.UTF_8))) {
            List<String> lines = br.lines()
                    .skip(1)
                    .filter(l -> !l.isBlank())
                    .toList();

            System.out.printf("%n%s      -CARGAR DEL CSV-%n", Colors.PURPLE);

            int loaded = 0;
            int skipped = 0;

            for (String line : lines) {
                String[] f = line.split(";");
                int nia = Integer.parseInt(f[0]);
                if (AlumnoDAO.exists(nia)) {
                    System.out.println(Colors.ORANGE + "NIA " + nia + " ya existe, se omite");
                    skipped++;
                    continue;
                }

                AlumnoDAO.createStudent(nia, f[1], f[2], f[3],
                        LocalDate.parse(f[4]), f[5], f[6], f[7]);
                loaded++;
            }

            System.out.println(Colors.GREEN + loaded + " alumnos cargados correctamente"
                    + (skipped > 0 ? Colors.ORANGE + " (" + skipped + " omitidos por NIA duplicado)" : ""));
        } catch (IOException e) {
            System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage() + Colors.RESET);

        }
    }

    private static void modifyStudent() throws IOException {
        if (AlumnoDAO.findStudents().isEmpty()) {
            System.out.println(Colors.RED + "No hay alumnos para modificar");
            return;
        }

        System.out.printf("%n%s-ACTUALIZAR NOMBRE (\"%s\" para cancelar)--%n", Colors.PURPLE, CANCEL_WORD);
        String onlyLetters = "solo se permiten letras y un único espacio entre palabras";
        int nia;

        try {
            while (true) {
                nia = ask("NIA", Main::parseNia);

                if (AlumnoDAO.exists(nia))
                    break;
                System.out.println(Colors.RED + "No existe un usuario con ese NIA, intentalo de nuevo\n");
            }

            String name = ask("Nombre", validated("Nombre", 50, LETTERS, onlyLetters));

            AlumnoDAO.modifyStudent(name, nia);
        } catch (CancelledException e) {
            System.out.println(Colors.RED + "Operación cancelada");
        }
    }

    private static void deleteStudentByNia() throws IOException {
        if (AlumnoDAO.findStudents().isEmpty()) {
            System.out.println(Colors.RED + "No hay alumnos para eliminar vía nia");
            return;
        }

        System.out.printf("%n%s-ELIMINAR ALUMNO VÍA NIA (\"%s\" para cancelar)--%n", Colors.PURPLE, CANCEL_WORD);
        int nia;

        try {
            while (true) {
                nia = ask("NIA", Main::parseNia);

                if (AlumnoDAO.exists(nia))
                    break;
                System.out.println(Colors.RED + "No existe un usuario con ese NIA, intentalo de nuevo\n");
            }

            AlumnoDAO.deleteStudentByNia(nia);
        } catch (CancelledException e) {
            System.out.println(Colors.RED + "Operación cancelada");
        }
    }

    private static void deleteStudentByLastName() throws IOException {
        if (AlumnoDAO.findStudents().isEmpty()) {
            System.out.println(Colors.RED + "No hay alumnos para eliminar vía apellido");
            return;
        }

        System.out.printf("%n%s-ELIMINAR ALUMNO VÍA APELLIDO (\"%s\" para cancelar)--%n", Colors.PURPLE, CANCEL_WORD);
        String onlyLetters = "solo se permiten letras y un único espacio entre palabras";
        String lastName;

        try {
            while (true) {
                lastName = ask("Apellidos", validated("Apellidos", 100, LETTERS, onlyLetters));
                int deleted = AlumnoDAO.deleteStudentByLastName(lastName);

                if (deleted > 0) {
                    System.out.println(Colors.GREEN + "Se " + (deleted == 1 ? "ha eliminado " : "han eliminado ")
                            + deleted + (deleted == 1 ? " alumno" : " alumnos")
                            + " con apellido " + lastName);
                    break;
                }

                System.out.println(Colors.RED + "No existe un usuario que su apellido contenga " + lastName
                        + " intentalo de nuevo\n");
            }
        } catch (CancelledException e) {
            System.out.println(Colors.RED + "Operación cancelada");
        }
    }

    private static void saveStudentsJson() {
        List<Alumno> students = AlumnoDAO.findStudents();
        if (students.isEmpty()) {
            System.out.println(Colors.RED + "No hay alumnos para guardar vía json");
            return;
        }

        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(STUDENTS_JSON.toFile(), students);
            System.out.printf("%n%s      -GUARDAR EN JSON-%n", Colors.PURPLE);
            System.out.println(Colors.GREEN + "Los alumnos se han guardado en " + STUDENTS_JSON);
        } catch (IOException e) {
            System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage() + Colors.RESET);
        }
    }

    private static void loadStudentsJson() {
        if (!Files.exists(STUDENTS_JSON)) {
            System.out.println(Colors.RED + "No existe el fichero JSON de alumnos");
            return;
        }

        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        try {
            List<Alumno> students = mapper.readValue(
                    STUDENTS_JSON.toFile(), new TypeReference<List<Alumno>>() {
                    });

            System.out.printf("%n%s      -CARGAR DEL JSON-%n", Colors.PURPLE);

            int loaded = 0;
            int skipped = 0;

            for (Alumno s : students) {
                if (AlumnoDAO.exists(s.getNia())) {
                    System.out.println(Colors.ORANGE + "NIA " + s.getNia() + " ya existe, se omite");
                    skipped++;
                    continue;
                }

                AlumnoDAO.createStudent(s.getNia(), s.getName(), s.getLastName(), s.getGender(),
                        s.getBirthDate(), s.getCycle(), s.getCourse(), s.getGroup());
                loaded++;
            }

            System.out.println(Colors.GREEN + loaded + " alumnos cargados correctamente"
                    + (skipped > 0 ? Colors.ORANGE + " (" + skipped + " omitidos por NIA duplicado)" : ""));

        } catch (IOException e) {
            System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage() + Colors.RESET);
        }
    }

    private static void options(int option) throws IOException {
        switch (option) {
            case 1 -> addStudent();
            case 2 -> AlumnoDAO.selectStudents();
            case 3 -> saveStudentsCsv();
            case 4 -> loadStudentsCsv();
            case 5 -> modifyStudent();
            case 6 -> deleteStudentByNia();
            case 7 -> deleteStudentByLastName();
            case 8 -> saveStudentsJson();
            case 9 -> loadStudentsJson();
            default -> System.err.println(Colors.RED + "Opción no disponible todavía." + Colors.RESET);
        }
    }

    public static void main(String[] args) {
        initialize();

        while (true) {
            try {
                System.out.printf(
                        "%n%s\t--OPCIONES--%n%s1.- Insertar alumno%n2.- Mostrar alumnos%n3.- Guardar en svg%n4.- Cargar del svg%n5.- Modificar nombre vía NIA%n6.- Eliminar alumno vía NIA%n7.- Eliminar alumno vía apellido%n8.- Guardar en json%n9.- Cargar del json%n%s0.- Salir%n%s Opción: ",
                        Colors.ORANGE, Colors.BLUE, Colors.RED, Colors.WHITE);

                String option = BR.readLine();
                System.out.print(Colors.RESET);

                if (option == null) {
                    System.out.println(Colors.RED + "\nSaliendo..." + Colors.RESET);
                    break;
                }

                if (!option.trim().matches("[0-9]")) {
                    throw new NumberFormatException("Debes introducir un número del 0 al 9");
                }

                int op = Integer.parseInt(option.trim());

                if (op == 0) {
                    System.out.println(Colors.RED + "Saliendo..." + Colors.RESET);
                    break;
                }

                options(op);
            } catch (IOException e) {
                System.err.println(Colors.RED + "Error de entrada/salida: " + e.getMessage() + Colors.RESET);
            } catch (NumberFormatException e) {
                System.err.println(Colors.RED + "Error de formato: " + e.getMessage() + Colors.RESET);
            } catch (IllegalArgumentException e) {
                System.err.println(Colors.RED + "Argumento no válido: " + e.getMessage() + Colors.RESET);
            }
        }

        System.exit(0);
    }
}