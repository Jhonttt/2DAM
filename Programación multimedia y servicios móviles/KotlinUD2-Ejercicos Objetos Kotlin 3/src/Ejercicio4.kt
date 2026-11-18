interface CargadorDatos {
    fun cargar(): String
    fun guardar(datos: String): Boolean
}

open class FuenteDatosBase(val nombreFuente: String) {
    open val esRemota: Boolean = false
    override fun toString(): String {
        return "Fuente: $nombreFuente | Remota: $esRemota"
    }
}

class AlmacenamientoPreferencias(nombreFuente: String) :
    FuenteDatosBase(nombreFuente), CargadorDatos {
    private var cache: String = ""
    override fun cargar(): String {
        println("Leyendo de Preferencias Locales ($nombreFuente)...")
        return cache.ifEmpty { "Sin datos" }
    }

    override fun guardar(datos: String): Boolean {
        this.cache = datos
        println("Guardado localmente en $nombreFuente: $datos")
        return true
    }
}

class ServicioApiRest : FuenteDatosBase, CargadorDatos {
    override val esRemota: Boolean = true
    // Como no hemos declarado constructor primario,
    // usamos un constructor secundario con super para invocar a la clase padre FuenteDatosBase
    constructor(endpoint: String) : super("API_REST: $endpoint")

    override fun cargar(): String {
        println("Realizando petición HTTP GET a $nombreFuente...")
        return "{ status: 200, body: 'JSON_DATA' }"
    }

    override fun guardar(datos: String): Boolean {
        println("Realizando petición HTTP POST a $nombreFuente con payload: $datos")
        return true
    }
}

fun main() {
    val prefs = AlmacenamientoPreferencias("UserSession")
    val api = ServicioApiRest("https://api.miapp.com/v1")
    val fuentes: List<FuenteDatosBase> = listOf(prefs, api)
    for (f in fuentes) {
        println(f.toString())
        if (f is CargadorDatos) {
            f.guardar("Token_ABC")
            println("Resultado: ${f.cargar()}\n")
        }
    }
}