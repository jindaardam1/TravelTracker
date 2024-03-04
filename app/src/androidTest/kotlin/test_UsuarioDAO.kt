import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import model.firebase.dao.UsuarioDAO
import org.junit.Test

class UsuarioDAOTest {

    @Test
    fun insert_new_user_with_valid_input_data() {
        // Arrange
        val usuarioDAO = UsuarioDAO()
        val nombreUsuario = "JohnDoe"
        val contra = "password123"
        val email = "johndoe@example.com"

        // Act
        runBlocking {
            usuarioDAO.insertarUsuario(nombreUsuario, contra, email)
        }

        // Assert
        // Verificar que el usuario se agregó a la base de datos
        // Puedes usar un mock para verificar la interacción con la base de datos
    }

    @Test
    fun authenticate_existing_user_with_valid_credentials() {
        // Arrange
        val usuarioDAO = UsuarioDAO()
        val nombreUsuario = "JohnDoe"
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
        val email = "johndoe@example.com"

        // Act
        runBlocking {
            usuarioDAO.insertarUsuario(nombreUsuario, contra, email)
        }

        // Assert
        // Verificar que el usuario no se agregó a la base de datos
        // Puedes usar un mock para verificar la interacción con la base de datos
    }

    @Test
    fun insert_new_user_with_empty_email() {
        // Arrange
        val usuarioDAO = UsuarioDAO()
        val nombreUsuario = "JohnDoe"
        val contra = "password123"
        val email = ""

        // Act
        runBlocking {
            usuarioDAO.insertarUsuario(nombreUsuario, contra, email)
        }

        // Assert
        // Verificar que el usuario no se agregó a la base de datos
        // Puedes usar un mock para verificar la interacción con la base de datos
    }

    @Test
    fun insert_new_user_with_empty_password() {
        // Arrange
        val usuarioDAO = UsuarioDAO()
        val nombreUsuario = "JohnDoe"
        val contra = ""
        val email = "johndoe@example.com"

        // Act
        runBlocking {
            usuarioDAO.insertarUsuario(nombreUsuario, contra, email)
        }

        // Assert
        // Verificar que el usuario no se agregó a la base de datos
        // Puedes usar un mock para verificar la interacción con la base de datos
    }
}
