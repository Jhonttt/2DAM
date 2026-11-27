fun main() {
    val interruptor = Interruptor()
    interruptor.pulsar { interruptor.encendido = !interruptor.encendido }
}

class Interruptor(var encendido: Boolean = false) {
    fun pulsar(onCambioEstado: (Boolean) -> Unit) = onCambioEstado
}
