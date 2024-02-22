package com.example.traveltracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val countries = resources.getStringArray(R.array.paises)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, countries)

        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewCountry)
        autoCompleteTextView.setAdapter(adapter)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            // Realizar la validación de entrada y la lógica de registro aquí
            val username = findViewById<EditText>(R.id.usernameEdit).text.toString()
            val email = findViewById<EditText>(R.id.emailEdit).text.toString()
            val password = findViewById<EditText>(R.id.passwordEdit).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.confirmEdit).text.toString()
            val selectedCountry = autoCompleteTextView.text.toString()

            // Validar la entrada aquí (por ejemplo, verificar si los campos están vacíos o si la contraseña coincide)
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || selectedCountry.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                // Verificar el formato del correo electrónico usando una expresión regular predefinida
                Toast.makeText(this, "Formato de correo electrónico inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                // Verificar que la contraseña tenga al menos 6 caracteres
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                // Verificar si la contraseña y su confirmación coinciden
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else
            {
                Toast.makeText(this, "TODO CORRECTO", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                // Funciona con esto, puede que no sea la mejor forma de hacerlo
                finish()
                startActivity(intent)


            }

            // Si la validación pasa, puedes proceder con el registro
            // Agrega aquí la lógica para el registro (por ejemplo, enviar los datos a un servidor) TODO

            // Una vez completado el registro, puedes navegar a otra actividad o realizar cualquier otra acción necesaria


        }
    }
}
