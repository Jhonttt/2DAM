fun main() {
    val tareas = mutableListOf(
        Tarea("Entregar práctica de MySQL", 5),
        Tarea("Revisar PR del proyecto", 4),
        Tarea("Actualizar el CV", 2),
        Tarea("Ordenar el escritorio", 1)
    )

    println("Antes de nada:")
    tareas.forEach(::println)
    tareas.filter { it.prioridad >= 4 }.forEach { it.urgente = true }
    println("Después de nada:")
    tareas.forEach(::println)
}

class Tarea(val descripcion: String, val prioridad: Int, var urgente: Boolean = false) {
    override fun toString(): String {
        return "Tarea(descripcion='$descripcion', prioridad=$prioridad, urgente=$urgente)"
    }
}

