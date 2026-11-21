class MascotaClass(val nombre: String, val edad: Int)
data class MascotaData(val nombre: String, val edad: Int)
fun main() {
 val perroClass = MascotaClass("Periko", 4)
 val perroData = MascotaData("Botarate", 1)
 println(perroClass)
 println(perroData)
}
