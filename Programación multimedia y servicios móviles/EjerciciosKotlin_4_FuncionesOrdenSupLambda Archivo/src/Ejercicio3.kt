fun main() {
    println(calcularPrecioFinal(1000.0, 10.0, ::descuentoPorcentaje))
    println(calcularPrecioFinal(1000.0, 10.0, ::descuentoFijo))
}

fun calcularPrecioFinal(precio: Double, valor: Double, politica: (Double, Double) ->
Double): Double = politica(precio, valor)

fun descuentoPorcentaje (precio: Double, valor: Double) = precio - precio * (valor / 100)
fun descuentoFijo (precio: Double, valor: Double) = if (precio - valor >= 0.0) precio - valor else 0.0