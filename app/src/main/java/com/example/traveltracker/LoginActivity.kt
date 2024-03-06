package com.example.traveltracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.firebase.dao.UsuarioDAO

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            // Inicialización del EditText después de inflar la vista
            editTextUsername = findViewById(R.id.editTextUsername)
            editTextPassword = findViewById(R.id.editTextPassword)
            //   var buttonLogin = findViewById(R.id.buttonLogin)
            // Llamar a la función iniciarSesion al hacer clic en un botón, por ejemplo
            // (asegúrate de tener un botón en tu layout y definir el evento onClick en XML o programáticamente)
            // Por simplicidad, aquí asumiremos que tienes un botón con ID "buttonLogin"
            findViewById<Button>(R.id.buttonLogin).setOnClickListener {
                iniciarSesion()
            }

            val botonRegistrarse = findViewById<Button>(R.id.registrarseButton)

            botonRegistrarse.setOnClickListener(){
                iraRegistrar()
            }
        } catch (e: Exception) {
            Log.e("Error to loco ahí", e.toString())
        }
    }
    private fun user(): Boolean {
        val username = editTextUsername.text.toString()
        //Aquí (en el if) hay que hacer una consulta a la base de datos la cual no sé como hacer "where user == user en la base de datos" o algo así
        //por el momento he puesto estas condiciones para que no de error
        if (username.length < 6 && username.length < 20) {
            Toast.makeText(this, "El nombre de usuario tiene que tener entre 6 y 20 caracteres", Toast.LENGTH_SHORT).show()
            return false;
        }
        else
            return  true;

    }
    private fun Contrasena(): Boolean {
        val contra = editTextPassword.text.toString()
        //Aquí (en el if) hay que hacer una consulta a la base de datos la cual no sé como hacer "where contra == contraenlabase de datos" o algo así
        //por el momento he puesto estas condiciones para que no de error
        if (contra.length < 6) {
            Toast.makeText(this, "La contraseña tiene que tener más de 6 caracteres", Toast.LENGTH_SHORT).show()
            return false;
        }
        else
            return  true;
    }
    private fun iniciarSesion() {
        if (Contrasena() && user()) {
            try {
                val usuarioDAO = UsuarioDAO()
                val contra = editTextPassword.text.toString()
                val nombreUsuario = editTextUsername.text.toString()

                var autentic: Boolean

                CoroutineScope(Dispatchers.Main).launch {
                    autentic = usuarioDAO.comprobarUsuario(nombreUsuario, contra)
                    if (autentic) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
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

    private fun iraRegistrar() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
    }
}
