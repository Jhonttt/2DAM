open class UsuarioBase(val idUsuario: String, val email: String) {
    open val nivelAcceso: Int = 1
    open fun validarPermiso(nivelRequerido: Int): Boolean {
        return nivelAcceso >= nivelRequerido
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UsuarioBase) return false
        return email == other.email
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }

    override fun toString(): String {
        return "${this::class.simpleName} (ID: $idUsuario, Email: $email, Nivel: $nivelAcceso)"
    }
}

class UsuarioBiometrico(
    idUsuario: String,
    email: String,
    val huellaRegistrada: Boolean
) : UsuarioBase(idUsuario, email) {
    override val nivelAcceso: Int = 2

    // Constructor secundario
    constructor(idUsuario: String, email: String) : this(idUsuario, email, true)

    fun autenticarHuella(): Boolean {
        return if (huellaRegistrada) {
            println("Autenticación biométrica exitosa para $email.")
            true
        } else {
            println("No hay huella registrada.")
            false
        }
    }
}

class UsuarioAdministrador(idUsuario: String, email: String) : UsuarioBase(
    idUsuario,
    email
) {
    override val nivelAcceso: Int = 10
    override fun validarPermiso(nivelRequerido: Int): Boolean {
        println("Acceso concedido automáticamente por rol Administrador.")
        return true
    }
}

fun main() {
    val uEstandar = UsuarioBase("U1", "periko@mail.com")
    val uBio = UsuarioBiometrico("U2", "ana@mail.com") // usa constructor secundario
    val uAdmin = UsuarioAdministrador("U3", "admin@mail.com")
    val lista = listOf(uEstandar, uBio, uAdmin)
    for (u in lista) {
        println(u)
        println("¿Puede acceder a módulo crítico (Nivel 5)?: ${u.validarPermiso(5)}")
        println("---")
    }
}
