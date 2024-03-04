package model.firebase.dao

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import model.firebase.entity.Usuario

class UsuarioDAO {

    internal val db = FirebaseFirestore.getInstance()

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
}
