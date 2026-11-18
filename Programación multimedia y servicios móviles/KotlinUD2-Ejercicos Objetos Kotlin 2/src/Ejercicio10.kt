class BilleteraVirtual(monedaInicial: String) {
    var saldo: Double = 0.0
        private set
    var moneda: String = "€"
        set(valor) {
            val monedaMayus = valor.uppercase().trim()
            if (monedaMayus in listOf("€", "$", "£")) {
                field = monedaMayus
            } else {
                println(
                    "ERROR: Moneda '$valor' no soportada. Se mantiene $field."
                )
            }
        }

    init {
        this.moneda = monedaInicial
    }

    val esCuentaVIP: Boolean
        get() = this.saldo > 10000.0 || this.moneda == "$"

    fun recargar(monto: Double) {
        if (monto > 0) {
            saldo += monto
            println("Recarga exitosa de $monto $moneda.")
        }
    }

    fun retirar(monto: Double) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto
            println("Retiro exitoso de $monto $moneda.")
        } else {
            println(
                "ERROR: Fondos insuficientes para retirar $monto $moneda.")
        }
    }
}

fun main() {
    val billetera = BilleteraVirtual("€")
    println(
        "Moneda: ${billetera.moneda} | Saldo: $ { billetera.saldo } | ¿Es VIP ?: ${billetera.esCuentaVIP}"
    )
    billetera.recargar(15000.0)
    println(
        "Saldo actual: ${billetera.saldo} ${billetera.moneda} | ¿Es VIP ?: ${billetera.esCuentaVIP}"
    )
    billetera.moneda = "₧" // Moneda no soportada
    println("Moneda final: ${billetera.moneda}")
}