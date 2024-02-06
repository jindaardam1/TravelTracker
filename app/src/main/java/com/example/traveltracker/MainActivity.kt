package com.example.traveltracker

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView

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
    }

}