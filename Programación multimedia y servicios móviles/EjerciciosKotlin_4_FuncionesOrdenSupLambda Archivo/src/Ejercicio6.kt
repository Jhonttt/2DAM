fun main() {
    println(if (valuarNumero(5, ::esPar)) "Par" else "Impar")
    println(if (valuarNumero(5, ::mayorQueCien)) "Es mayor que cien" else "No es mayor que cien")
}

fun valuarNumero(num: Int, condicion: (Int) -> Boolean) = condicion(num)

fun esPar(num: Int): Boolean = num % 2 == 0
fun mayorQueCien(num: Int): Boolean = num > 100
