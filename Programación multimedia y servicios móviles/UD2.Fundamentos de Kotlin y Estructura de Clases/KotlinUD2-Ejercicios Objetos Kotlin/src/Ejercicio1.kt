import utils.Colors

class Persona(val nombre: String, var edad: Int) {
    fun cumplirAnios() {
        edad++
        println("${Colors.GREEN}Felicidades $nombre por tus $edad años")
    }
}

fun main() {
    Persona("Juan", 20).cumplirAnios();
}