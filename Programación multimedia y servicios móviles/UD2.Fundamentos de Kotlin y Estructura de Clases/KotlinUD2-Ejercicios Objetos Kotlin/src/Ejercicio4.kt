class ConfiguracionSistema private constructor(val clave: String) {
 constructor() : this("CLAVE_DEFECTO") {
 println("Configuración inicializada mediante constructor secundario.")
 }
}
fun main() {
 val config = ConfiguracionSistema()
 println("Clave activa: ${config.clave}")
 // ERROR DE COMPILACIÓN si intentamos llamar al primario:
 // val configDirecta = ConfiguracionSistema("MI_CLAVE")
 // "Cannot access 'constructor': it is private in 'ConfiguracionSistema'"
}
