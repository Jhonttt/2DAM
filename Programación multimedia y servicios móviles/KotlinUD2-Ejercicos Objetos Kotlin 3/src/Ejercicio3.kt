class Termostato(temperatura: Double = 20.0) {
    var temperatura = temperatura
        set(value) = if (value in -10.0..45.0) field = value else println("Temperatura invalida")
}

fun main() {
    val termostato = Termostato()
    termostato.temperatura = 22.0
    termostato.temperatura = -22.0
}