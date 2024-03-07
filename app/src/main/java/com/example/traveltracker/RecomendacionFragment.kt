package com.example.traveltracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.traveltracker.R

class RecomendacionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recomendacion, container, false)

        // Referenciar vistas
        val nombreTextView: TextView = view.findViewById(R.id.Nombre)
        val imageView: ImageView = view.findViewById(R.id.imageView3)
        val descripcionTextView: TextView = view.findViewById(R.id.Descripcion)

        // Obtener datos de la recomendación (nombre, imagen y descripción)
        val nombreRecomendacion = "Cuba" // Cambiar por el nombre real
        val imagenURL = "https://media.traveler.es/photos/61376e02c6202df7591601a1/master/w_1920%2Cc_limit/138178.jpg" // Cambiar por la URL real de la imagen
        val descripcionRecomendacion = "Cuba, oficialmente la República de Cuba, es un país soberano insular del Caribe, asentado en un archipiélago del mar Caribe. Su capital y ciudad más poblada es La Habana." +
                " Cuba es conocida por su rica historia, su cultura única, sus playas hermosas y su música vibrante, incluido el son cubano y la salsa. Explora la mezcla de arquitectura colonial española y la influencia caribeña en sus ciudades, como Trinidad y Santiago de Cuba."

        // Asignar el nombre a TextView
        nombreTextView.text = nombreRecomendacion

        // Cargar imagen desde la URL usando Glide
        Glide.with(requireContext())
            .load(imagenURL)
            .into(imageView)

        // Asignar la descripción a TextView
        descripcionTextView.text = descripcionRecomendacion

        return view
    }
}

