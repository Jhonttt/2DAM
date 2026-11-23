package es.ies.kotlin

import kotlin.system.exitProcess

private val NOTAS: Int = 4;

fun main() {
    println(WHITE + "Introduce tus 4 notas:" + RESET)
    var suma: Double = 0.0

    for (i in 1..NOTAS) {
        while (true) {
            print(BLUE + "Nota $i: " + RESET)
            val nota: Double = readlnOrNull()?.toDoubleOrNull() ?: -1.0

            if (nota in 0.0..10.0) {
                suma += nota
                break
            }
            println(RED + "Error introduce un numero valido (0.0-10.0)\n" + RESET)
        }
    }

    val promedio: Double = suma / NOTAS
    println(WHITE + "\nEl promedio es de " + BLUE + promedio + RESET)

    if (promedio < 5) {
        println(RED + "Suspenso" + RESET)
        exitProcess(0)
    }
    println(GREEN + "Aprobado" + RESET)
    exitProcess(0)
}