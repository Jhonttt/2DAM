import java.util.Locale.getDefault

fun main() {
    println(formatearTexto("Holi que tal") {s -> s.uppercase(getDefault()) + "!!!" })
    println(formatearTexto("Holi que tal") {s -> s.replace(" ", "_") })
    println(formatearTexto("Holi que tal") {s -> s.take(3) })
}

fun formatearTexto(texto: String, formato: (String) -> String): String = formato(texto)