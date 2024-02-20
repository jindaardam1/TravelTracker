package com.example.traveltracker

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import model.database.LocalDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.searchView)
        resultTextView = findViewById(R.id.textView12)

        // Configurar un listener para el cambio en el texto de búsqueda
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Puedes realizar acciones cuando se envía la búsqueda (pulsar Enter)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Actualizar el texto en el TextView
                resultTextView.text = "$newText"
                return true
            }
        })
        setContentView(R.layout.activity_main)

        try {
            LocalDatabase.getInstance(this)
            Log.i("Info", "Conexión establecida con la db")
        } catch (e: Exception) {
            Log.e("Error", e.toString())
        }
    }

}