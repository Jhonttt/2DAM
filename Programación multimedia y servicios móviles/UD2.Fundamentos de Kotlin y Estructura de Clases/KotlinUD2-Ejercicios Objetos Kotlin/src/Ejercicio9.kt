class Empleado(val nombre: String, val puesto: String, val salarioBase: Double) {
    init {
        require(salarioBase > 0.0) { "El salario base debe ser mayor a 0." }
    }

    constructor(nombre: String) : this(nombre, "Becario", 1000.0) {
        println("Empleado registrado con valores por defecto para becarios.")
    }

    constructor(nombre: String, puesto: String) : this(nombre, puesto, 1800.0) {
        println("Empleado registrado con puesto $puesto y salario base estándar.")
    }
}

fun main() {
    val becario1 = Empleado(nombre = "Periquín")
    val nombre = becario1.nombre
    val puesto = becario1.puesto
    val sueldo = becario1.salarioBase
    println("Nombre: $nombre | Puesto: $puesto | Sueldo: $sueldo")
    val vendedor1 = Empleado(nombre = "Periko", "Vendedor")
    println("${vendedor1.nombre} | ${vendedor1.puesto} | $vendedor1.sueldo")
}
