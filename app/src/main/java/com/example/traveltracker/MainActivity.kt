package com.example.traveltracker

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseApp
import model.local.database.LocalDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var myImageView: ImageView
    private lateinit var navigation_option1: ImageView
    private lateinit var navigation_option2: ImageView
    private lateinit var navigation_option3: ImageView
    private lateinit var navigation_option4: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)

        try {
            var db = LocalDatabase.getInstance(this)
            Log.i("Database", "Base de datos creada con éxito")
            db.estadoPaisDao().getAll()
        } catch (e: Exception) {
            Log.e("Error", e.toString())
        }

        // Encontrar vistas por ID
        myImageView = findViewById(R.id.navigation_option1) // Cambiar al ID correcto si es diferente
        navigation_option1 = findViewById(R.id.navigation_option1)
        navigation_option2 = findViewById(R.id.navigation_option2)
        navigation_option3 = findViewById(R.id.navigation_option3)
        navigation_option4 = findViewById(R.id.navigation_option4)

        // Cambiar color de navigation_option1 por defecto
        changeColor(navigation_option1, R.color.lightblue)

        // Establecer controladores de clic para cada ImageView
        navigation_option1.setOnClickListener {
            changeColor(navigation_option1, R.color.lightblue)
            loadFragment(MapaFragment())
        }
        navigation_option2.setOnClickListener {
            changeColor(navigation_option2, R.color.lightblue)
            loadFragment(OfertasFragment())
        }
        navigation_option3.setOnClickListener {
            changeColor(navigation_option3, R.color.lightblue)
            loadFragment(RecomendacionFragment())
        }
        navigation_option4.setOnClickListener {
            changeColor(navigation_option4, R.color.lightblue)
            loadFragment(PerfilFragment())
        }

        // Cargar el fragmento inicial
        loadFragment(MapaFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun changeColor(imageView: ImageView, colorResourceId: Int) {
        // Restablecer el color de todos los botones
        navigation_option1.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.SRC_IN)
        navigation_option2.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.SRC_IN)
        navigation_option3.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.SRC_IN)
        navigation_option4.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.SRC_IN)

        // Cambiar el color del botón pulsado
        imageView.setColorFilter(resources.getColor(colorResourceId), PorterDuff.Mode.SRC_IN)
    }
}
