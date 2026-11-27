fun main() {
    val empleados = listOf(
        Empleado("Ana García", "IT", 3200.0),
        Empleado("Luis Pérez", "IT", 2800.0),
        Empleado("Marta López", "Ventas", 2400.0),
        Empleado("Carlos Ruiz", "Ventas", 2600.0),
        Empleado("Elena Sanz", "RRHH", 2500.0)
    )

    val empleadosNombres = empleados.filter { it.departamento == "IT" }.map { it.nombre.uppercase() }
    print(empleadosNombres)
}

class Empleado(val nombre: String, val departamento: String, val salario: Double)