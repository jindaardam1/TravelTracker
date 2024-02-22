package com.example.traveltracker

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import model.database.LocalDatabase


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            LocalDatabase.getInstance(this)
            Log.i("Database", "Base de datos creada con éxito")
        } catch (e: Exception) {
            Log.e("Error", e.toString())
        }

        // Encontrar vistas por ID
//        val navigation_option1: ImageView = findViewById(R.id.navigation_option1)
//        val navigation_option2: TextView = findViewById(R.id.navigation_option2)
//        val navigation_option3: TextView = findViewById(R.id.navigation_option3)
//        val navigation_option4: TextView = findViewById(R.id.navigation_option4)
//
//        // Establecer controladores de clic para cada TextView
//        navigation_option1.setOnClickListener { loadFragment(MapaFragment()) }
//        navigation_option2.setOnClickListener { loadFragment(OfertasFragment()) }
//        navigation_option3.setOnClickListener { loadFragment(RecomendacionFragment()) }
//        navigation_option4.setOnClickListener { loadFragment(PerfilFragment()) }

        // Cargar el fragmento inicial (puedes cambiarlo según tus necesidades)
        loadFragment(MapaFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
