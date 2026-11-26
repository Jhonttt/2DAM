fun main() {
    println("${convertirTemperatura(100.0, ::celsiusFahrenheit)} fahrenheit")
    print("${convertirTemperatura(100.0, ::celsiusKelvin)} kelvin")
}

fun celsiusFahrenheit(c: Double): Double = (c * 1.8 + 32)
fun celsiusKelvin(c: Double): Double = (c + 273.15)

fun convertirTemperatura(grados: Double, formula: (Double) -> Double): Double = formula(grados)
