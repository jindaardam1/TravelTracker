package model.firebase.entity

import java.security.MessageDigest
import java.util.Locale

class Usuario(usuario: String, contra: String, email: String) {
    val usuario: String = usuario.trim().uppercase()
    val email: String = email.trim().uppercase()
    val contraEncriptada: String = encriptarSHA512(contra)

    private fun encriptarSHA512(input: String): String {
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance("SHA-512")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}
