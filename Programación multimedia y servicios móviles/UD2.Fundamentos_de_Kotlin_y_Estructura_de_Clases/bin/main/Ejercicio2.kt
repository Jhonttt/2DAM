package es.ies.kotlin

import kotlin.system.exitProcess

fun main() {
    println(WHITE + "Introduce un numero entero entre 1-99:" + RESET)

    while (true) {
        print(BLUE + "Numero: " + RESET)
        val num: Int = readlnOrNull()?.toIntOrNull() ?: -1

        if (num in 1..99) {
            if (num.toString().length == 1) print("${WHITE}EL numero $BLUE$num$WHITE tiene $GREEN${num.toString().length}$WHITE gigito")
            else print("${WHITE}El numero $BLUE$num$WHITE tiene $GREEN${num.toString().length}$WHITE gigitos$RESET")
            exitProcess(0)
        }
        println(RED + "Error introduce un numero valido (0-99)\n" + RESET)
    }
}