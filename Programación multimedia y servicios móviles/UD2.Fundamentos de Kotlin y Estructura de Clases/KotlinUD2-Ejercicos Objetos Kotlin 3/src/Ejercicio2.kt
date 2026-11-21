import kotlin.random.Random

interface Notificable {
    val prioridad: String
    fun enviar(mensaje: String)
    fun mostrarLog(mensaje: String) {
        println("[LOG ${prioridad}]: $mensaje")
    }
}

open class NotificacionBase(val idNotificacion: Int, val titulo: String)

class NotificacionPush(
    val tokenDispositivo: String, idNotificacion: Int, titulo: String
) : NotificacionBase(idNotificacion, titulo), Notificable {
    override val prioridad: String = "ALTA"

    override fun enviar(mensaje: String) {
        super.mostrarLog(mensaje)
    }
}

class NotificacionLocal(
    idNotificacion: Int, titulo: String
) : NotificacionBase(idNotificacion, titulo), Notificable {
    constructor(titulo: String) : this(Random.nextInt(1, 10000), titulo)
    override val prioridad: String = "BAJA"

    override fun enviar(mensaje: String) {
        super.mostrarLog(mensaje)
    }
}

fun main() {
    val push = NotificacionPush("abc123", 1, "Oferta")
    val local = NotificacionLocal("Recordatorio")

    push.enviar("Tienes una nueva oferta")
    local.enviar("Reunión en 10 minutos")
}