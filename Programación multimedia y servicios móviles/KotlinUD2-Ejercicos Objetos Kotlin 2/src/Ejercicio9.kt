class HabitacionHotel(val numero: Int) {
    var huesped: String = "Sin Ocupar"
        set(valor) {
            val limpio = valor.trim()
            field = if (limpio.isBlank()) {
                "Sin Ocupar"
            } else {
                limpio.lowercase().replaceFirstChar { it.uppercase() }
            }
        }
    var diasEstancia: Int = 1
        set(valor) {
            if (valor >= 1) {
                field = valor
            } else {
                println("ERROR: La estancia mínima es 1 día.")
            }
        }
    val costoTotal: Double
        get() = this.diasEstancia * 65.0
}

fun main() {
    val h = HabitacionHotel(101)
    println(
        "Habitación ${h.numero}: Huésped '${h.huesped}' | Días: ${h.diasEstancia} | Total: $ { h.costoTotal } €"
    )
    h.huesped = " mARíA gARCíA "
    h.diasEstancia = 4
    println(
        "Habitación ${h.numero}: Huésped '${h.huesped}' | Días: ${h.diasEstancia} | Total: $ { h.costoTotal } €"
    )
}