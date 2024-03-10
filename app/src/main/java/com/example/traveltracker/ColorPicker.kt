package com.example.traveltracker

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.traveltracker.R
import com.example.traveltracker.PerfilFragment
import com.skydoves.colorpickerview.ColorPickerView

class ColorPickerActivity : AppCompatActivity() {

    private lateinit var colorPickerView: ColorPickerView
    private lateinit var btnAceptar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.color_picker) // Reemplaza "tu_layout_xml" con el nombre real de tu archivo XML

        colorPickerView = findViewById(R.id.colorPickerView)
        btnAceptar = findViewById(R.id.btnAceptar)
        btnCancelar = findViewById(R.id.btnCancelar)

        btnAceptar.setOnClickListener {
            // Aquí puedes realizar acciones al hacer clic en el botón Aceptar
            // Por ejemplo, cargar un nuevo fragmento o realizar cualquier otra acción
            loadFragment(PerfilFragment())
        }

        btnCancelar.setOnClickListener {
            // Aquí puedes realizar acciones al hacer clic en el botón Cancelar
            // Por ejemplo, cargar un nuevo fragmento o realizar cualquier otra acción
            loadFragment(PerfilFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
