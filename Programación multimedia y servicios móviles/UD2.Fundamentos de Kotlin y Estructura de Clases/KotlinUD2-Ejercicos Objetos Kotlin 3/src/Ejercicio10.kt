interface Conectable {
    fun conectar(): Boolean
    fun desconectar()
    fun verificarEstado() {
        println("Estado del enlace BLE: Transmisión activa.")
    }
}

open class DispositivoBLE(val macAddress: String, open val nombre: String) {
    open val nivelBateria: Int = 100
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DispositivoBLE) return false
        return macAddress == other.macAddress
    }

    override fun hashCode(): Int {
        return macAddress.hashCode()
    }

    override fun toString(): String {
        return "$nombre ($macAddress) - Batería: $nivelBateria%"
    }
}

class BandaCardiaca(
    macAddress: String,
    nombre: String,
    override var nivelBateria: Int
) : DispositivoBLE(macAddress, nombre), Conectable {
    // Constructor secundario
    constructor(macAddress: String) : this(macAddress, "Banda HRM Genérica", 100)

    override fun conectar(): Boolean {
        println("Conectando servicio Heart Rate Monitor a $macAddress...")
        return true
    }

    override fun desconectar() {
        println("Desconectado de la banda cardíaca $nombre.")
    }
}

class RelojInteligente(
    macAddress: String,
    nombre: String,
    val pantallaOled: Boolean
) : DispositivoBLE(macAddress, nombre), Conectable {
    override fun conectar(): Boolean {
        verificarEstado() // Llamada al método con implementación por defecto de la
        interfaz
        println("Sincronizando notificaciones con el reloj $nombre ($macAddress)...")
        return true
    }

    override fun desconectar() {
        println("Reloj $nombre desconectado.")
    }
}

fun main() {
    val banda1 = BandaCardiaca("00:11:22:33:FF:EE", "Polar H10", 85)
    val banda2 = BandaCardiaca("44:55:66:77:AA:BB") // Usa constructor secundario
    val smartwatch = RelojInteligente("88:99:AA:BB:CC:DD", "Galaxy Watch", true)
    println(banda1)
    banda1.conectar()
    banda1.nivelBateria = 80 // Se puede modificar porque se declaró con 'override var'
    println("Batería actualizada: ${banda1.nivelBateria}%")
    println("\n" + smartwatch)
    smartwatch.conectar()
    println("\n¿Son la misma banda? ${banda1 == banda2}") // Test equals
}