class ProductoTienda(precioInicial: Double) {
    var precio: Double = precioInicial
        get() = field * 1.10 // Devuelve el precio base + 10% de recargo al leer
        set(valor) {
            if (valor >= 0) {
                field = valor
            } else {
                println("ERROR: El precio no puede ser negativo. Se mantiene en $field.")
            }
        }
}

fun main() {
    val p = ProductoTienda(100.0)
    println("Precio de venta (base + 10%): ${"%.2f".format(p.precio)} €")
    p.precio = -50.0 // Intento inválido
    println(
        "Precio tras intento negativo: ${
            String.format(
                java.util.Locale.US,
                "%.2f", p.precio
            )
        } €"
    )
    p.precio = 200.0 // Intento válido
    println("Nuevo precio de venta: ${p.precio} €")
}