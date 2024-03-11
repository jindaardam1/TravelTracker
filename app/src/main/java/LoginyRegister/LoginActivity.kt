package LoginyRegister

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.traveltracker.MainActivity
import com.example.traveltracker.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.firebase.dao.UsuarioDAO

/**
 * Actividad que maneja el inicio de sesión de los usuarios.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText

    /**
     * Método que se llama cuando se crea la actividad.
     * @param savedInstanceState Estado de la instancia anterior de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            checkCameraPermission()

            checkLocationPermission()

            editTextUsername = findViewById(R.id.editTextUsername)
            editTextPassword = findViewById(R.id.editTextPassword)

            findViewById<Button>(R.id.buttonLogin).setOnClickListener {
                iniciarSesion()
            }

            val botonRegistrarse = findViewById<Button>(R.id.registrarseButton)

            botonRegistrarse.setOnClickListener {
                iraRegistrar()
            }
        } catch (e: Exception) {
            Log.e("Error to loco ahí", e.toString())
        }
    }

    /**
     * Verifica si el nombre de usuario cumple con los requisitos.
     * @return true si el nombre de usuario es válido, false de lo contrario.
     */
    private fun user(): Boolean {
        val username = editTextUsername.text.toString()

        return if (username.length < 6 && username.length < 20) {
            Toast.makeText(this, "El nombre de usuario tiene que tener entre 6 y 20 caracteres", Toast.LENGTH_SHORT).show()
            false
        } else
            true
    }

    /**
     * Verifica si la contraseña cumple con los requisitos.
     * @return true si la contraseña es válida, false de lo contrario.
     */
    private fun contrasena(): Boolean {
        val contra = editTextPassword.text.toString()

        return if (contra.length < 6) {
            Toast.makeText(this, "La contraseña tiene que tener más de 6 caracteres", Toast.LENGTH_SHORT).show()
            false
        } else
            true
    }

    /**
     * Inicia sesión en la aplicación.
     */
    private fun iniciarSesion() {
        if (contrasena() && user()) {
            try {
                val usuarioDAO = UsuarioDAO()
                val contra = editTextPassword.text.toString()
                val nombreUsuario = editTextUsername.text.toString()

                var autentic: Boolean

                CoroutineScope(Dispatchers.Main).launch {
                    autentic = usuarioDAO.comprobarUsuario(nombreUsuario, contra)
                    if (autentic) {

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)

                        intent.putExtra("username", nombreUsuario)

                        startActivity(intent)

                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Usuario o contraseña inválidos.", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                Log.e("Error al insertar en Firebase", e.toString())
            }
        }
    }

    /**
     * Navega a la pantalla de registro de usuario.
     */
    private fun iraRegistrar() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    private val CAMERA_PERMISSION_REQUEST_CODE = 1001
    private val LOCATION_PERMISSION_REQUEST_CODE = 1002

    /**
     * Verifica si se tiene el permiso de ubicación y solicita permisos si es necesario.
     */
    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    /**
     * Verifica si se tiene el permiso de cámara y solicita permisos si es necesario.
     */
    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        } else {
            checkLocationPermission()
        }
    }

    /**
     * Se llama cuando se obtiene el resultado de la solicitud de permisos.
     * @param requestCode Código de solicitud.
     * @param permissions Arreglo de permisos solicitados.
     * @param grantResults Arreglo de resultados de los permisos solicitados.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkLocationPermission()
                } else {
                    Toast.makeText(this@LoginActivity, "El permiso es necesario para el correcto funcionamiento", Toast.LENGTH_LONG).show()
                }
            }
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    // No es necesario hacer nada
                } else {
                    Toast.makeText(this@LoginActivity, "El permiso es necesario para el correcto funcionamiento", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
