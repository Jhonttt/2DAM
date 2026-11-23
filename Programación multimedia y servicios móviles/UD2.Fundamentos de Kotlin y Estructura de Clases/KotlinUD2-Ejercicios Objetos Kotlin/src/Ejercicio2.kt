import utils.Colors

class Automovil(val marca: String, val anio: Int? = 2026) {
    var mutable: Boolean = false

    fun arrancar() {
        mutable = true
        println("${Colors.GREEN}$marca del $anio esta ${if (mutable) "encendido" else "apagado"}")
    }
}

fun main() {
    Automovil("VolksWagen").arrancar()
}