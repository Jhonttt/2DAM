import java.time.LocalDate

interface ProcesadorPago {
    fun procesar(monto: Double): Boolean
    fun emitirRecibo(monto: Double, moneda: String) {
        val hoy: LocalDate = LocalDate.now()
        println("--- RECIBO DE PAGO ---")
        println("Fecha: $hoy")
        println("Importe abonado: $monto $moneda")
        println("Estado: COMPLETADO")
        println("----------------------")
    }
}

open class Transaccion(open val idTransaccion: String, open val moneda: String = "€") {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Transaccion) return false
        return idTransaccion == other.idTransaccion
    }

    override fun hashCode(): Int {
        return idTransaccion.hashCode()
    }
}

class PagoGooglePay(
    idTransaccion: String,
    val correoCuenta: String,
    override val moneda: String = "$"
) : Transaccion(idTransaccion, moneda), ProcesadorPago {
    override fun procesar(monto: Double): Boolean {
        println("Validando Token de Google Pay para la cuenta $correoCuenta...")
        println("Cobro de $monto $moneda procesado con éxito.")
        emitirRecibo(monto, moneda)
        return true
    }
}

class PagoTarjeta(
    idTransaccion: String,
    val numeroTarjeta: String,
    val cvv: Int
) : Transaccion(idTransaccion), ProcesadorPago {
    // Constructor secundario que delega al primario
    constructor(numeroTarjeta: String, cvv: Int) : this(
        "TX-" + numeroTarjeta.takeLast(4),
        numeroTarjeta,
        cvv
    )

    override fun procesar(monto: Double): Boolean {
        println(
            "Contactando con pasarela bancaria para tarjeta * * * *$ { numeroTarjeta.takeLast(4) }...")
        println("Transacción $idTransaccion aprobada por $monto $moneda.")
        emitirRecibo(monto, moneda)
        return true
    }
}

fun main() {
    val gpay = PagoGooglePay("GP-9981", "usuario@gmail.com")
    val tarjeta = PagoTarjeta("4540123456788899", 123)
    gpay.procesar(19.99)
    println()
    tarjeta.procesar(149.50)
}
