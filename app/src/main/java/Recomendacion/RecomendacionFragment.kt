package Recomendacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
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

        val nombreTextView: TextView = view.findViewById(R.id.Nombre)
        val imageView: ImageView = view.findViewById(R.id.imageView3)
        val recyclerViewSitiosInteres: RecyclerView = view.findViewById(R.id.recyclerViewSitiosInteres)

        val emoji = "\uD83C\uDFD6"
        val nombreRecomendacion = "$emoji CUBA $emoji"
        val imagenURL = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Flag_of_Cuba.svg/800px-Flag_of_Cuba.svg.png"


        nombreTextView.text = nombreRecomendacion

        Glide.with(requireContext())
            .load(imagenURL)
            .into(imageView)


        val sitiosInteres = generarSitiosInteres()
        val adapter = SitioInteresAdapter(requireContext(), sitiosInteres)
        recyclerViewSitiosInteres.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewSitiosInteres.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.divider)
        drawable?.let {
            dividerItemDecoration.setDrawable(it)
        }
        recyclerViewSitiosInteres.addItemDecoration(dividerItemDecoration)

        return view
    }

    //ASIGNAR ACTIVIDADES DE INTERES
    fun generarSitiosInteres(): List<SitioInteres> {
        return listOf(
            SitioInteres("Restaurante: La Habana Vieja", R.mipmap.habana_vieja, "https://havanavieja.com/"),
            SitioInteres("Lugar: Plaza de la Revolución", R.mipmap.plaza_revolucion, "https://www.visitarcuba.org/plaza-de-la-revolucion"),
            SitioInteres("Lugar: Malecón de La Habana", R.mipmap.malecon, "https://www.visitarcuba.org/el-malecon-de-la-habana"),
            SitioInteres("Hotel: Hotel Vedado", R.mipmap.hotel, "https://www.grancaribehotels.com/hoteles-y-destinos/la-habana/hotel-vedado"),

            SitioInteres("Lugar: Playa Varadero", R.mipmap.playa_varadero, "https://www.meliacuba.com/es/destinos/varadero/guia"),
            SitioInteres("Restaurante: La Carretera", R.mipmap.la_carretera, "http://www.carretacubana.com/"),
            SitioInteres("INFO: Descubre sobre la historia de Cuba", R.mipmap.info, "https://viajes.nationalgeographic.com.es/p/cuba"),

        )
    }

}
