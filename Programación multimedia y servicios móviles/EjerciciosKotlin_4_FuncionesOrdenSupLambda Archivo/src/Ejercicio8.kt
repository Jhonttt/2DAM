fun main() {
    val notas = listOf(4.5, 7.0, 9.2, 3.8, 5.0, 6.4, 2.1)
    val notasAprobados = notas.filter { it >= 5.0 }
    val notasSobresalientes = notasAprobados.filter { it >= 9.0 }
    println("Notas aprobadas: $notasAprobados")
    print("Notas sobresaliente: $notasSobresalientes")
}