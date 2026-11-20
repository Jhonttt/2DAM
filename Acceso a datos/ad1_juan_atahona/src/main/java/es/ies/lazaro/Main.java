package es.ies.lazaro;

public class Main {

    public static void main(String[] args) {
        // * Ejercico 1
        Ejercicio1.main(args);
        // ? RA1.1
        /*
         * if (a.getEdad() < 1 || a.getEdad() > 120) {
         * Se puede leer edad porque en xml esta permitido usar una edad negativa no es
         * ningún tipo de error.
         * Se rechaza la tercera solicitud porque una de las validaciones que se ha
         * considerado es no permitir edades negativas, solo las que están entre 1 y 120
         * años.
         */
        // ? RA1.2
        /*
         * Falla en mi función validateXml() entra en este catch:
         * catch (IOException e)
         * {
         * error("Error de entrada salida: " + e.getMessage());
         * }
         * y eso hace que devuelva un false la función, y la función de crearCsv() solo
         * sucede si es true, es decir si todo ha ido bien
         */

        // * Ejercico 2
        // Ejercicio2.main(args);
        // ? RA2.1
        /*
         * Enlazo el ciclo a ciclo = ? con ps.setString y la edad mínima a edad >= ? con
         * ps.setInt
         * Devuelve:
         * ID NOMBRE EDAD CICLO
         * ---------------------------
         * 1 Álex Ruiz 19 DAM
         */
        // ? RA2.2
        /*
         * No se muestra el id como confirmado porque hay que esperar a que todos estén
         * correctamente insertados y una vez todos han sido correctamente añadidos
         * entonces se muestran los id generados.
         * Mi DAO ejecuta anadirAlumnos(List<AlumnoXML> alumnos) sobre
         * ConnectionDB.getConnectionDB() la cual es la conexión a la base de datos
         * donde tengo puestas las credenciales en el env:
         * SERVER_URL=jdbc:mysql://192.168.56.10:3306/
         * URL=jdbc:mysql://192.168.56.10:3306/instituto
         * USER=alumno
         * PASS=admin
         */

    }

}