open class ComponenteView(val id: String) {
    open val esClickable: Boolean = false
    var ancho: Int = 100
    var alto: Int = 50
    open fun dibujar() {
        println("Renderizando Componente ID: $id [$ancho x $alto]")
    }
}

open class TextView(id: String, open var texto: String) : ComponenteView(id) {
    override fun dibujar() {
        super.dibujar()
        println(" -> Texto: \"$texto\"")
    }
}

class Button : TextView {
    override val esClickable: Boolean = true

    // Constructor secundario 1: delega en super (TextView)
    constructor(id: String, texto: String) : super(id, texto)

    // Constructor secundario 2: delega en el constructor secundario 1 mediante this()
    constructor(id: String) : this(id, "Aceptar")

    override fun dibujar() {
        println("[BOTÓN CLICKABLE = $esClickable]")
        super.dibujar()
        println(" -> Visual: [=== $texto ===]")
    }

    fun hacerClick() {
        if (esClickable) {
            println("¡Evento OnClick en el botón $id!")
        }
    }
}

fun main() {
    val label = TextView("txtBienvenida", "Bienvenido a la App")
    val btnGuardar = Button("btnGuardar", "Guardar Cambios")
    val btnDefecto = Button("btnAnonimo")
    label.dibujar()
    println("---")
    btnGuardar.dibujar()
    btnGuardar.hacerClick()
    println("---")
    btnDefecto.dibujar()
}