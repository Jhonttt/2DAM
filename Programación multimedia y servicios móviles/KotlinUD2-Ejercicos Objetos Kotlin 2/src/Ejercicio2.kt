class Estudiante(nombreInicial: String, notaBase: Double) {
    var nombre: String = nombreInicial
        get() = field.uppercase().trim()
        set(value) {
            if (value.isEmpty()) println("El nombre del estudiante no puede estar vacío") else field = value
        }
}

fun main() {
    val estudiante = Estudiante("Luis", 3.2)
    println(estudiante.nombre)
    estudiante.nombre = ""
    println(estudiante.nombre)
    estudiante.nombre = "Pedro"
    println(estudiante.nombre)
}