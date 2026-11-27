fun main() {
    val palabras = listOf("Kotlin", "Android", "App", "Lambda", "UI", "Funcional")
    val palabrasFiltradas = palabras.map { it.length }.filter { it % 2 == 0 }
    println(palabrasFiltradas)
}