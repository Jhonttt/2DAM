class VehiculoElectrico(var nivelBateria: Int) {
    val estadoBateria: String
        get() = when (this.nivelBateria) {
            in 0..14 -> "Crítico"
            in 15..49 -> "Bajo"
            in 50..100 -> "Óptimo"
            else -> "Desconocido"
        }
}

fun main() {
    val coche = VehiculoElectrico(80)
    println("Nivel batería: ${coche.nivelBateria}%: Estado: ${coche.estadoBateria}")
    coche.nivelBateria = 10
    println("Nivel batería: ${coche.nivelBateria}%: Estado: ${coche.estadoBateria}")
}