open class Sensor(val id: String, open val frecuenciaHz: Int) {
    var activo: Boolean = false

    override fun toString(): String {
        return "[Sensor ID: $id] Frec: ${frecuenciaHz}Hz | Activo: $activo"
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Sensor) return false
        return id == other.id
    }
}


class SensorAcelerometro(id: String, override var frecuenciaHz: Int) : Sensor(id, frecuenciaHz) {
    constructor(id: String) : this(id, 50)

    fun leerDatos(): String {
        if (!activo) return "Sensor inactivo: sin datos"
        return "Sensor activo: ${frecuenciaHz.toString()}Hz"
    }
}

fun main() {
    val a1 = SensorAcelerometro("A1", 100)
    val a2 = SensorAcelerometro("A2")
    println(a1)
    println(a2)

    println(a1.leerDatos())
    a1.activo = true
    println(a1.leerDatos())

    a1.frecuenciaHz = 200
    println(a1)
}