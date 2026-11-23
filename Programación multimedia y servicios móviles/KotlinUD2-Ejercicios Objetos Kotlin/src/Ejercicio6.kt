class LibroClass(val id: Int, val titulo: String)
data class LibroDataClass(val id: Int, val titulo: String)

fun main() {
    val libroComun1 = LibroClass(1, "Don Quijote")
    val libroComun2 = LibroClass(1, "Don Quijote")
    val libroData1 = LibroDataClass(1, "Don Quijote")
    val libroData2 = LibroDataClass(1, "Don Quijote")
    println("¿Son iguales los LibroClass? ${libroComun1 == libroComun2}")
    // false (apuntan a instancias de memoria distintas)
    println("¿Son iguales los LibroDataClass? ${libroData1 == libroData2}")
    // true (tienen las mismas propiedades)
    println(
        "¿Son iguales los LibroDataClass y los LibroClass? ${
            libroComun1 ==
                    libroData1
        }"
    )
    // false (son instancias de 2 clases distintas)
}