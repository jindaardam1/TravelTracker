package com.example.traveltracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.traveltracker.R


class RecomendacionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recomendacion, container, false)

        // Referenciar vistas
        val nombreTextView: TextView = view.findViewById(R.id.Nombre)
        val imageView: ImageView = view.findViewById(R.id.imageView3)
        val descripcionTextView: TextView = view.findViewById(R.id.Descripcion)
        val recyclerViewSitiosInteres: RecyclerView = view.findViewById(R.id.recyclerViewSitiosInteres)

        // Obtener datos de la recomendación (nombre, imagen y descripción)
        val nombreRecomendacion = "Cuba" // Cambiar por el nombre real
        val imagenURL = "https://media.traveler.es/photos/61376e02c6202df7591601a1/master/w_1920%2Cc_limit/138178.jpg"
        val descripcionRecomendacion = "Cuba, oficialmente la República de Cuba, es un país soberano insular del Caribe, asentado en un archipiélago del mar Caribe. Su capital y ciudad más poblada es La Habana."


        // Dar nombre (Cuba)
        nombreTextView.text = nombreRecomendacion

        // Cargar imagen desde internet con la URL usando Glide
        Glide.with(requireContext())
            .load(imagenURL)
            .into(imageView)

        // Asignar la descripción a TextView (Textaco)
        descripcionTextView.text = descripcionRecomendacion


        // Configurar RecyclerView de sitios de interés
        val sitiosInteres = generarSitiosInteres()
        val adapter = SitioInteresAdapter(requireContext(), sitiosInteres)
        recyclerViewSitiosInteres.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewSitiosInteres.adapter = adapter
        return view
    }

    //ASIGNAR ACTIVIDADES DE INTERES
    private fun generarSitiosInteres(): List<SitioInteres> {
        return listOf(
            SitioInteres("Restaurante: La Habana Vieja", R.mipmap.habana_vieja, "https://havanavieja.com/"),
            SitioInteres("Lugar: Plaza de la Revolución", R.mipmap.plaza_revolucion, "https://www.visitarcuba.org/plaza-de-la-revolucion"),
          SitioInteres("Lugar: Malecón de La Habana", R.mipmap.malecon, "https://www.visitarcuba.org/el-malecon-de-la-habana"),
           SitioInteres("Hotel: Hotel Vedado", R.mipmap.hotel, "https://www.grancaribehotels.com/hoteles-y-destinos/la-habana/hotel-vedado")
        )
    }

}
