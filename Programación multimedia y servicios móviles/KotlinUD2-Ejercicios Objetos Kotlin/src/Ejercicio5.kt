class Curso(val nombre: String, val cupoMaximo: Int) {
    init {
        require(!nombre.isBlank()) { "El nombre del curso no puede estar vacío." }
        println("Primer init: Nombre del curso verificado.")
    }

    init {
        require(cupoMaximo >= 5) { "El cupo mínimo para abrir un curso es de 5 estudiantes." }
        println("Segundo init: Cupo máximo verificado.")
    }
}

fun main() {
    val cursoValido = Curso("Kotlin desde Cero", 20)
    // Provocará una excepción IllegalArgumentException:
    // val cursoInvalido = Curso("Android UI", 3)
}
