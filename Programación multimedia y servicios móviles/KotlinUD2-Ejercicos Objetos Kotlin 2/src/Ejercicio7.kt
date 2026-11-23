class UsuarioApp(emailInicial: String) {
    var emailVerificado: Boolean = false
        private set
    var email: String = ""
        set(valor) {
            if (valor.contains("@")) {
                field = valor // Asignación directa a memoria sin invocar set() de nuevo
                emailVerificado = true
            } else {
                println("ERROR: Formato de email inválido ($valor). No se actualiza.")
            }
        }

    init {
        this.email = emailInicial // Ejecuta el set() desde la inicialización
    }
}

fun main() {
    val u = UsuarioApp("usuario@correo.com")
    println("Email: ${u.email} | ¿Verificado?: ${u.emailVerificado}")
    u.email = "correo_invalido.com" // Rechazado
    println("Email actual: ${u.email} | ¿Verificado?: ${u.emailVerificado}")
}