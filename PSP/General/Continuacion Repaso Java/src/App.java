import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        try {
            ArrayList<Alumno> alumnos = new ArrayList<>();
            double total = 0;

            alumnos.add(new Alumno("María", 5));
            alumnos.add(new Alumno("José", 4));
            alumnos.add(new Alumno("Juan", 2));
            alumnos.add(new Alumno("Laura", 8));
            alumnos.add(new Alumno("Francisco", 4));
            alumnos.add(new Alumno("María", 10));

            for (Alumno al : alumnos) {
                total += al.getNotaPSP();
            }

            System.out.printf("La nota media de los %s alumnos es: %.2f.%n", alumnos.size(), total / alumnos.size());
            System.out.print("El nombre de los alumnos analizados es: ");

            for (int i = 0; i < alumnos.size(); i++) {
                System.out.printf("%s%s", alumnos.get(i).getNombre(), i < alumnos.size() - 1 ? ", " : ".");
            }
           
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
