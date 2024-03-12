package model.firebase.dao

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import model.firebase.entity.Usuario

/**
 * Clase de acceso a datos (DAO) para la gestión de usuarios en Firebase Firestore.
 *
 * @property db Instancia de FirebaseFirestore para la comunicación con Firestore.
 */
class UsuarioDAO {

    internal val db = FirebaseFirestore.getInstance()

    /**
     * Inserta un nuevo usuario en la colección "usuarios" de Firestore.
     *
     * @param nombreUsuario Nombre de usuario del nuevo usuario.
     * @param contra Contraseña del nuevo usuario.
     * @param email Dirección de correo electrónico del nuevo usuario.
     */
    suspend fun insertarUsuario(nombreUsuario: String, contra: String, email: String) {
        val usuario = Usuario(nombreUsuario, contra, email)

        val datosUsuario = hashMapOf(
            "usuario" to usuario.usuario,
            "email" to usuario.email,
            "contra" to usuario.contraEncriptada
        )

        try {
            db.collection("usuarios")
                .add(datosUsuario)
                .await()
            Log.i("Usuario añadido", "Usuario añadido correctamente")
        } catch (e: Exception) {
            Log.e("Error", "Error al añadir usuario: $e")
        }
    }

    /**
     * Comprueba si las credenciales de un usuario son válidas.
     *
     * @param nombreUsuario Nombre de usuario a verificar.
     * @param contra Contraseña a verificar.
     * @return true si las credenciales son válidas, false de lo contrario.
     */
    suspend fun comprobarUsuario(nombreUsuario: String, contra: String): Boolean {
        val usuario = Usuario(nombreUsuario, contra, "")

        return try {
            val querySnapshot = db.collection("usuarios")
                .whereEqualTo("usuario", usuario.usuario)
                .whereEqualTo("contra", usuario.contraEncriptada)
                .get()
                .await()

            val usuarioExiste = !querySnapshot.isEmpty
            if (usuarioExiste) {
                Log.i("Usuario autenticado", "Usuario autenticado correctamente")
            } else {
                Log.i("Credenciales incorrectas", "Credenciales de usuario incorrectas")
            }
            usuarioExiste
        } catch (e: Exception) {
            Log.e("Error al comprobar usuario", "Error al comprobar usuario: $e")
            false
        }
    }

    /**
     * Comprueba si un nombre de usuario ya existe en la colección "usuarios".
     *
     * @param nombreUsuario Nombre de usuario a verificar.
     * @return true si el nombre de usuario ya existe, false de lo contrario.
     */
    suspend fun comprobarSiUsuarioExiste(nombreUsuario: String): Boolean {
        val usuario = Usuario(nombreUsuario, "", "")

        return try {
            val querySnapshot = db.collection("usuarios")
                .whereEqualTo("usuario", usuario.usuario)
                .get()
                .await()

            val usuarioExiste = !querySnapshot.isEmpty
            usuarioExiste
        } catch (e: Exception) {
            Log.e("Error al comprobar usuario", "Error al comprobar usuario: $e")
            false
        }
    }

    /**
     * Comprueba si una dirección de correo electrónico ya está en uso.
     *
     * @param email Dirección de correo electrónico a verificar.
     * @return true si el email está en uso, false de lo contrario.
     */
    suspend fun comprobarSiEmailEstaEnUso(email: String): Boolean {
        val usuario = Usuario("", "", email)

        return try {
            val querySnapshot = db.collection("usuarios")
                .whereEqualTo("email", usuario.email)
                .get()
                .await()

            val emailEnUso = !querySnapshot.isEmpty
            emailEnUso
        } catch (e: Exception) {
            Log.e("Error al comprobar email", "Error al comprobar email: $e")
            false
        }
    }
}
