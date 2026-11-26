fun main() {
    println("La contraseña${if (validarPassword("hola que tal estas", ::esLongitudValida)) "" else " no"} tiene más de 8 caracteres")
    println("La contraseña${if (validarPassword("hola que tal estas", ::tieneNumero)) "" else "no"} tiene algún número")
}

fun esLongitudValida(pass: String): Boolean = pass.length > 8
fun tieneNumero(pass: String): Boolean = pass.any() { it.isDigit() }

fun validarPassword(pass: String, regla: (String) -> Boolean): Boolean = regla(pass)