package com.example.traveltracker

import LoginyRegister.LoginActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * Fragmento que representa la pantalla de perfil de usuario.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PerfilFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var nombreUsuario: TextView

    /**
     * Método llamado para crear la vista del fragmento.
     *
     * @param inflater El inflador utilizado para inflar el diseño.
     * @param container El contenedor padre en el que se debe colocar el diseño inflado.
     * @param savedInstanceState El estado previamente guardado del fragmento.
     * @return La vista inflada para el fragmento.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)
        nombreUsuario = view.findViewById<TextView>(R.id.nombreUsuario)

        val args = arguments
        if (args != null && args.containsKey("username")) {
            val username = args.getString("username")
            Log.e("CONTROL 2", "username2: $username")
            val textViewUsername = view.findViewById<TextView>(R.id.nombreUsuario)
            textViewUsername.text = username
        }

        val botonVisitados = view.findViewById<Button>(R.id.btnSeleccionarColorPaisesVisitados)
        val botonVisitando = view.findViewById<Button>(R.id.btnSeleccionarColorPaisesVisitando)
        val botonVerificado = view.findViewById<Button>(R.id.btnPaisesVerificados)
        val botonCerrarSesion = view.findViewById<Button>(R.id.Cerrar_sesion)
        val colorPickerView = view.findViewById<com.skydoves.colorpickerview.ColorPickerView>(R.id.colorPickerView)
        val relativeLayout = view.findViewById<RelativeLayout>(R.id.relativeLayout)
        val btnAceptar = view.findViewById<Button>(R.id.btnAceptar)
        val txtVisitados = view.findViewById<TextView>(R.id.textoVisitados)
        val txtVerificados = view.findViewById<TextView>(R.id.textoVerificados)
        val txtVisitando = view.findViewById<TextView>(R.id.textoVisitando)
        btnAceptar.setOnClickListener {
            relativeLayout.visibility = View.VISIBLE
            colorPickerView.visibility = View.GONE
            btnAceptar.visibility = View.GONE
            txtVisitados.visibility = View.GONE
            txtVerificados.visibility = View.GONE
            txtVisitando.visibility = View.GONE
        }
        botonVisitados.setOnClickListener {
            relativeLayout.visibility = View.GONE
            colorPickerView.visibility = View.VISIBLE
            btnAceptar.visibility = View.VISIBLE
            txtVisitados.visibility = View.VISIBLE
        }
        botonVisitando.setOnClickListener {
            relativeLayout.visibility = View.GONE
            colorPickerView.visibility = View.VISIBLE
            btnAceptar.visibility = View.VISIBLE
            txtVisitando.visibility = View.VISIBLE
        }
        botonVerificado.setOnClickListener {
            relativeLayout.visibility = View.GONE
            colorPickerView.visibility = View.VISIBLE
            btnAceptar.visibility = View.VISIBLE
            txtVerificados.visibility = View.VISIBLE
        }
        botonCerrarSesion.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }
}
