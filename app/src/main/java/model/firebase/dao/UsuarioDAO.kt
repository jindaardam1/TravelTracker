package model.firebase.dao

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import model.firebase.entity.Usuario

class UsuarioDAO {

    private val db = FirebaseFirestore.getInstance()

    suspend fun insertarUsuario(nombreUsuario: String, contra: String, email: String) {
        val usuario = Usuario(nombreUsuario, contra, email)

        val datosUsuario = hashMapOf(
            "usuario" to usuario.usuario,
            "email" to usuario.email,
            "contraEncriptada" to usuario.contraEncriptada
        )

        try {
            db.collection("usuarios")
                .add(datosUsuario)
                .await()
            println("Usuario añadido correctamente")
        } catch (e: Exception) {
            println("Error al añadir usuario: $e")
        }
    }

    suspend fun comprobarUsuario(nombreUsuario: String, contra: String) {
        val usuario = Usuario(nombreUsuario, contra, "")

        try {
            val querySnapshot = db.collection("usuarios")
                .whereEqualTo("usuario", usuario.usuario)
                .whereEqualTo("contraEncriptada", usuario.contraEncriptada)
                .get()
                .await()

            if (!querySnapshot.isEmpty) {
                println("Usuario autenticado correctamente")
            } else {
                println("Credenciales de usuario incorrectas")
            }
        } catch (e: Exception) {
            println("Error al comprobar usuario: $e")
        }
    }
}
