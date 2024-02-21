package com.example.traveltracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val countries = resources.getStringArray(R.array.paises)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, countries)

        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewCountry)
        autoCompleteTextView.setAdapter(adapter)



    }
}