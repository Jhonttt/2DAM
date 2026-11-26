fun main() {
    val cuadrado: (Int) -> Int = { n: Int -> n * n }
    println(cuadrado(10))
    val esPositivo: (Int) -> Boolean = { n: Int -> n > 0}
    println(if (esPositivo(-3)) "Positivo" else "Negativo")
    val saludar = { nombre: String, hora: Int -> if (hora < 12) "Buenos días $nombre" else "Buenas tardes $nombre"}
    println(saludar("Luis", 12))
}