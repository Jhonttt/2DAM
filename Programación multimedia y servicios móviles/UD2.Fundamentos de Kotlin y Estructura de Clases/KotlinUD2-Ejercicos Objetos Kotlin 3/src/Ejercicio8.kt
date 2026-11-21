interface DibujableEnMapa {
    val latitud: Double
    val longitud: Double
    fun renderizarIcono()
    fun obtenerCoordenadas(): String {
        return "($latitud, $longitud)"
    }
}

open class MarcadorBase(
    override val latitud: Double,
    override val longitud: Double,
    val titulo: String
) : DibujableEnMapa {
    override fun renderizarIcono() {
        println("Pin estándar rojo en ${obtenerCoordenadas()} para '$titulo'")
    }

    override fun toString(): String {
        return "[Marcador] $titulo en ${obtenerCoordenadas()}"
    }
}

class MarcadorGasolinera(
    latitud: Double,
    longitud: Double,
    titulo: String,
    val precio95: Double
) : MarcadorBase(latitud, longitud, titulo) {
    override fun renderizarIcono() {
        println("⛽ Icono Surtidor ($precio95€/L) en ${obtenerCoordenadas()} - $titulo")
    }
}

class MarcadorRestaurante : MarcadorBase {
    val tenedores: Int

    constructor(latitud: Double, longitud: Double, titulo: String, tenedores: Int)
            : super(latitud, longitud, titulo) {
        this.tenedores = tenedores
    }

    // Constructor secundario para restaurantes no geolocalizados
    constructor(titulo: String, tenedores: Int) : super(0.0, 0.0, titulo) {
        this.tenedores = tenedores
    }

    override fun renderizarIcono() {
        val estrellas = "🍴".repeat(tenedores)
        println("$estrellas Restaurante '$titulo' en ${obtenerCoordenadas()}")
    }
}

fun main() {
    val gasolinera1 = MarcadorGasolinera(40.4167, -3.7037, "Repsol Centro", 1.659)
    val restaurante1 = MarcadorRestaurante(40.4180, -3.7020, "Casa Lucio", 3)
    val restaurante2 = MarcadorRestaurante("Bar Manolo", 1) // Usando constructor
    secundario
    val mapa: List<DibujableEnMapa> = listOf(gasolinera1, restaurante1, restaurante1)
    for (item in mapa) {
        println(item.toString())
        item.renderizarIcono()
        println("---")
    }
}