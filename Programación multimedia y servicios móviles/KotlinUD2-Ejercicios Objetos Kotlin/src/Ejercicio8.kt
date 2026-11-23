import kotlin.math.sqrt
import kotlin.math.pow

class TrianguloRectangulo(val base: Double, val altura: Double) {
    init {
        require(base > 0 && altura > 0) {
            "La base y la altura deben ser valores positivos." }
        }
        val hipotenusa: Double
        //get() = sqrt((this.base * this.base) + (this.altura * this.altura))
        get() = sqrt((this.base.pow(2.0)) + (this.altura.pow(2.0)))
        val area: Double
        get() = (this.base * this.altura) / 2.0
    }

    fun main() {
        val triangulo1 = TrianguloRectangulo(base = 22.0, altura = 33.0)
        val hipotenusa1 = triangulo1.hipotenusa
        println("La hipotenusa es ${"%.2f".format(hipotenusa1)}")
        println("El área es ${"%.2f".format(triangulo1.area)}")
    }