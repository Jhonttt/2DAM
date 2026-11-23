import kotlin.random.Random

interface Ejecutable {
    fun ejecutar(): Boolean
    fun cancelar() {
        println("Tarea cancelada y limpiando estado.")
    }
}

open class TareaWorkManager(val idWorker: String) {
    open val requiereRed: Boolean = false
    var reintentos: Int = 0
    override fun toString(): String {
        return "Worker ID: $idWorker | Red Requerida: $requiereRed | Reintentos: $reintentos"
    }
}

class TareaSincronizacionServidor(idWorker: String) :
    TareaWorkManager(idWorker), Ejecutable {
    override val requiereRed: Boolean = true
    override fun ejecutar(): Boolean {
        return if (requiereRed) {
            println("[$idWorker] Conectando al servidor y subiendo base de datos SQLite...")
            true
        } else {
            println("[$idWorker] Error: Se requiere conexión a Internet.")
            reintentos++
            false
        }
    }
}

class TareaLimpiezaCache : TareaWorkManager, Ejecutable {
    // Constructor secundario delegando en super directamente
    constructor() : super("CLEAN_TASK_" + Random.nextInt(100, 999))

    // Constructor secundario explícito pasándole ID personalizable
    constructor(customId: String) : super(customId)

    override fun ejecutar(): Boolean {
        println("[$idWorker] Borrando archivos temporales del directorio /cache...")
        return true
    }
}

fun main() {
    val sync = TareaSincronizacionServidor("SYNC_01")
    val cleanup = TareaLimpiezaCache() // Usando constructor secundario sin parámetros
    val colaTareas: List<Ejecutable> = listOf(sync, cleanup)
    for (tarea in colaTareas) {
        println(tarea.toString())
        val exito = tarea.ejecutar()
        println("¿Resultado OK?: $exito")
        println("---")
    }
}