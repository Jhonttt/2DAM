fun main() {
    val productos = listOf(
        Producto("Teclado mecánico", 15),
        Producto("Ratón inalámbrico", 0),
        Producto("Monitor 27\"", 4),
        Producto("Webcam HD", 0),
        Producto("Auriculares", 32)
    )

    productos.filter { it.stock in 1..5 }.forEach { println("AVISO: Quedan solo ${it.stock} unidades de ${it.nombre}") }
}

class Producto(val nombre: String, val stock: Int)