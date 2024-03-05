
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import model.firebase.dao.UsuarioDAO
import org.junit.Test

class UsuarioDAOTest {

    @Test
    fun insert_new_user_with_valid_input_data() {
        // Arrange
        val usuarioDAO = UsuarioDAO()
        val nombreUsuario = "DavidGurucharri"
        val contra = "password123"
        val email = "DavidGurucharri@mariaanasanz.com"

        // Act
        runBlocking {
            usuarioDAO.insertarUsuario(nombreUsuario, contra, email)
        }

        // Assert
        // Verificar que el usuario se agregó a la base de datos
    }

    @Test
    fun authenticate_existing_user_with_valid_credentials() {
        // Arrange
        val usuarioDAO = UsuarioDAO()
        val nombreUsuario = "DavidGurucharri"
        val contra = "password123"

        // Act
        val isAuthenticated = runBlocking {
            usuarioDAO.comprobarUsuario(nombreUsuario, contra)
        }

        // Assert
        assertTrue(isAuthenticated)
    }

    @Test
    fun insert_new_user_with_empty_username() {
        // Arrange
        val usuarioDAO = UsuarioDAO()
        val nombreUsuario = ""
        val contra = "password123"
        val email = "DavidGurucharri@mariaanasanz.com"

        // Act
        runBlocking {
            usuarioDAO.insertarUsuario(nombreUsuario, contra, email)
        }

        // Assert
        // Verificar que el usuario no se agregó a la base de datos
    }

    @Test
    fun insert_new_user_with_empty_email() {
        // Arrange
        val usuarioDAO = UsuarioDAO()
        val nombreUsuario = "DavidGurucharri"
        val contra = "password123"
        val email = ""

        // Act
        runBlocking {
            usuarioDAO.insertarUsuario(nombreUsuario, contra, email)
        }

        // Assert
        // Verificar que el usuario no se agregó a la base de datos
    }

    @Test
    fun insert_new_user_with_empty_password() {
        // Arrange
        val usuarioDAO = UsuarioDAO()
        val nombreUsuario = "DavidGurucharri"
        val contra = ""
        val email = "DavidGurucharri@mariaanasanz.com"

        // Act
        runBlocking {
            usuarioDAO.insertarUsuario(nombreUsuario, contra, email)
        }

        // Assert
        // Verificar que el usuario no se agregó a la base de datos
    }

    @Test
    fun test_comprobar_usuario_with_valid_credentials() {
        // Arrange
        val usuarioDAO = UsuarioDAO()
        val nombreUsuario = "jagobainda"

        val isAuthenticated = runBlocking {
            usuarioDAO.comprobarSiUsuarioExiste(nombreUsuario)
        }

        println(isAuthenticated.toString())
        assertTrue(isAuthenticated)
    }

    @Test
    fun test_comprobar_usuario_with_invalid_credentials() {
        // Arrange
        val usuarioDAO = UsuarioDAO()
        val nombreUsuario = "fhdajkfhdajkbdhafdfhad"

        val isAuthenticated = runBlocking {
            usuarioDAO.comprobarSiUsuarioExiste(nombreUsuario)
        }


        assertFalse(isAuthenticated)
    }

    @Test
    fun test_comprobar_email_with_valid_credentials() {
        // Arrange
        val usuarioDAO = UsuarioDAO()
        val email = "jagobainda@gmail.com"

        val isAuthenticated = runBlocking {
            usuarioDAO.comprobarSiEmailEstaEnUso(email)
        }

        assertTrue(isAuthenticated)
    }

    @Test
    fun test_comprobar_email_with_invalid_credentials() {
        // Arrange
        val usuarioDAO = UsuarioDAO()
        val email = "fhdajkfhdajkbdhafdfhad@gmail.com"

        val isAuthenticated = runBlocking {
            usuarioDAO.comprobarSiUsuarioExiste(email)
        }

        assertFalse(isAuthenticated)
    }
}
