class TarjetaCredito(limiteInicial: Double) {
    var deuda: Double = 0.0
        private set
    var limite: Double = limiteInicial
        set(valor) {
            if (valor >= deuda) {
                field = valor
            } else {
                println("ERROR: No se puede bajar el límite a $valor € (deuda actual: $deuda €).")
            }
        }
    val creditoDisponible: Double
        get() = this.limite - this.deuda

    fun realizarCompra(monto: Double) {
        if (monto <= creditoDisponible) {
            deuda += monto
            println("Compra de $monto € realizada con éxito.")
        } else {
            println(
                "ERROR: Compra de $monto € rechazada. Crédito disponible: $creditoDisponible €."
            )
        }
    }
}

fun main() {
    val tarjeta = TarjetaCredito(1000.0)
    println("Límite: ${tarjeta.limite} € | Disponible: ${tarjeta.creditoDisponible} €")
    tarjeta.realizarCompra(400.0)
    println("Deuda actual: ${tarjeta.deuda} € | Disponible: ${tarjeta.creditoDisponible} €")
    tarjeta.limite = 300.0 // Intento inválido (menor a la deuda de 400)
    println("Límite final: ${tarjeta.limite} €")
}