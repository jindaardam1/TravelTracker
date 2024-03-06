package com.example.traveltracker

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class OpcionesActivity : AppCompatActivity() {

    private var colorPaisesVisitados = Color.WHITE
    private var colorPaisesPorVisitar = Color.WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)

        val btnSeleccionarColorPaisesVisitados: Button = findViewById(R.id.btnSeleccionarColorPaisesVisitados)
        val btnSeleccionarColorPaisesPorVisitar: Button = findViewById(R.id.btnSeleccionarColorPaisesPorVisitar)
        btnSeleccionarColorPaisesVisitados.setOnClickListener {
            showColorPickerDialog { color ->
                colorPaisesVisitados = color
                // Aquí puedes hacer algo con el color seleccionado para PaisesVisitados
            }
        }

        btnSeleccionarColorPaisesPorVisitar.setOnClickListener {
            showColorPickerDialog { color ->
                colorPaisesPorVisitar = color
                // Aquí puedes hacer algo con el color seleccionado para PaisesPorVisitar
            }
        }
        val switch: Switch = findViewById(R.id.switch1)

        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                window.decorView.setBackgroundColor(Color.BLACK)
            } else {
                window.decorView.setBackgroundColor(Color.WHITE)
            }
        }
    }

    private fun showColorPickerDialog(listener: (color: Int) -> Unit) {
        /*
        ColorPickerDialogBuilder
            .with(this)
            .setTitle("Seleccionar color")
            .initialColor(Color.WHITE)
            .wheelType(ColorPickerDialog.WHEEL_TYPE.FLOWER)
            .density(12)
            .setPositiveButton("Aceptar", ColorPickerClickListener { _, color, _ ->
                listener(color)
            })
            .setNegativeButton("Cancelar") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .build()
            .show()

       */
    }

    public fun getColorPaisesVisitados(): Int {
        return colorPaisesVisitados
    }

    fun setColorPaisesVisitados(color: Int) {
        colorPaisesVisitados = color
    }

    fun getColorPaisesPorVisitar(): Int {
        return colorPaisesPorVisitar
    }

    fun setColorPaisesPorVisitar(color: Int) {
        colorPaisesPorVisitar = color
    }
}
