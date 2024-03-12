package model.firebase.entity

import java.security.MessageDigest

/**
 * Clase que representa la entidad de Usuario para ser almacenada en Firebase Firestore.
 *
 * @property usuario Nombre de usuario.
 * @property email Dirección de correo electrónico.
 * @property contraEncriptada Contraseña encriptada mediante el algoritmo SHA-512.
 */
class Usuario(usuario: String, contra: String, email: String) {
    val usuario: String = usuario.trim().uppercase()
    val email: String = email.trim().uppercase()
    val contraEncriptada: String = encriptarSHA512(contra)

    /**
     * Encripta una cadena de texto utilizando el algoritmo SHA-512.
     *
     * @param input Texto a encriptar.
     * @return Representación en formato hexadecimal de la cadena encriptada.
     */
    private fun encriptarSHA512(input: String): String {
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance("SHA-512")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}
