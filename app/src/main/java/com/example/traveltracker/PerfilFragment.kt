package com.example.traveltracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PerfilFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var nombreUsuario: TextView

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
        val  botonVerificado = view.findViewById<Button>(R.id.btnPaisesVerificados)
        val botonCerrarSesion = view.findViewById<Button>(R.id.Cerrar_sesion)
        botonVisitados.setOnClickListener {
            val intent = Intent(activity, ColorPickerActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        botonVisitando.setOnClickListener {
            val intent = Intent(activity, ColorPickerActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        botonVerificado.setOnClickListener {
            val intent = Intent(activity, ColorPickerActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        botonCerrarSesion.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PerfilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
