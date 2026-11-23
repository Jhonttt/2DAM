data class Pedido(val id: Int, val producto: String, val estado: String)

fun main() {
    val pedidoOriginal = Pedido(
        id = 101, producto = "Portátil", estado =
            "Pendiente"
    )
    val pedidoEnviado = pedidoOriginal.copy(estado = "Enviado")
    println("Pedido Original:")
    println(
        "ID: ${pedidoOriginal.id} | Producto: ${pedidoOriginal.producto} | Estado : $ { pedidoOriginal.estado }")
    val (idPedido, nombreProducto, estadoActual) = pedidoEnviado
    println("Pedido Enviado:")
    println("ID: $idPedido | Producto: $nombreProducto | Estado: $estadoActual")
}