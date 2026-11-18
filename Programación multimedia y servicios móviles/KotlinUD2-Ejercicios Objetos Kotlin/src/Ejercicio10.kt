class CuentaBancaria2(titularInicial: String, saldoInicial: Double) {
    var titular: String = titularInicial
        get() = field.uppercase()
        set(valor) {
            if (valor.isBlank()) {
                println("El nombre del titular no puede estar vacío.")
            } else {
                field = valor
            }
        }
    var saldo: Double = saldoInicial
        private set

    init {
        require(saldoInicial >= 0.0) { "El saldo inicial no puede ser negativo." }
    }

    fun ingresar(cantidad: Double) {
        if (cantidad > 0) {
            saldo += cantidad
            println("Ingreso realizado. Nuevo saldo: $saldo €")
        } else {
            println("El monto a ingresar debe ser mayor que 0.")
        }
    }

    fun retirar(cantidad: Double) {
        if (cantidad <= 0) {
            println("El monto a retirar debe ser mayor que 0.")
        } else if (cantidad > saldo) {
            println("Saldo insuficiente. Saldo actual: $saldo €")
        } else {
            saldo -= cantidad
            println("Retiro realizado. Nuevo saldo: $saldo €")
        }
    }
}

fun main() {
    val cta1 = CuentaBancaria2(titularInicial = "Periko", saldoInicial = 2000.0)
    println("Cuenta creada: ")
    println("Titular: ${cta1.titular} | Saldo: ${cta1.saldo}")
    cta1.retirar(25000.0)
    cta1.ingresar(50000.0)
    println("Titular: ${cta1.titular} | Saldo: ${cta1.saldo}")
    cta1.retirar(3000.0)
}