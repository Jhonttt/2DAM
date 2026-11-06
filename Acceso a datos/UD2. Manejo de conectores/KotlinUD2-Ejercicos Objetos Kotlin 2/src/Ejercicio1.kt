class Rectangulo(val ancho: Double, val alto: Double) {
    val area: Double
        get() = this.ancho * this.alto
}

fun main() {
    print(Rectangulo(2.0, 2.0).area)
}