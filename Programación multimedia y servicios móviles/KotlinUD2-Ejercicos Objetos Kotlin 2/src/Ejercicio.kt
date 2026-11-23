class ContadorPasos {
    // Lectura pública, modificación privada
    var pasos: Int = 0
        private set

    fun darPaso() {
        pasos += 1
    }

    fun reiniciar() {
        pasos = 0
    }
}

fun main() {
    val contador = ContadorPasos()
    contador.darPaso()
    contador.darPaso()
    println("Pasos actuales: ${contador.pasos}")
    for (i in 1..10) {
        contador.darPaso()
    }
    println("Pasos actuales: ${contador.pasos}")
    contador.reiniciar()
    println("Pasos tras reiniciar: ${contador.pasos}")
    // Si intentáramos: contador.pasos = 100
    // Daría error de compilación porque el setter es privado.
}