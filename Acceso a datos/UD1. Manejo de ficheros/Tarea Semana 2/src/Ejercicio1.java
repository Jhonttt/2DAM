public class Ejercicio1 {
    public static void main(String[] args) throws Exception {
        //? C:\Users\ana\Documents\notas.txt - Ruta absoluta ya que va desde el disco C hasta la ruta que se quiere mencionar, es decir todo el camino entero, haciendo imposible que no se sepa donde esta dando igual donde se este posicionado
        //? datos\clientes.txt - Ruta relativa ya que solo se mencionan dos rutas, esas mismas rutas pueden exitir en otros sitios por eso no es absoluta
        //? /home/alumno/proyecto/datos.csv - Ruta absoluta por lo mismo que en la primera opción, solo que esto es en linux, y se empieza en "/", pero es basicamente lo mismo
        //? imagenes\logo.png - Ruta relativa por lo mismo que se menciono antes, esa ruta puede exitir muchas veces ya que no se especifica la ruta absoluta, y de hecho solo tiene importancia cuando se esta posicionado en cierto sitio, es decir si esa ruta estuviera en Documents y estas en Documents tiene sentido, pero si estas en Downloads no se puede acceder al ser relativa, por eso hay que tener cuidado con ellas
    }
}
;