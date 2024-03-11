package Ofertas

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.traveltracker.R
import kotlinx.coroutines.launch
import kotlin.random.Random

class OfertasFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var searchView: SearchView
    private lateinit var adapter: ArrayAdapter<Oferta>
    private var logoRyanair: Drawable? = null
    private var logoIberia: Drawable? = null
    private var logoAirEuropa: Drawable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ofertas, container, false)

        listView = view.findViewById(R.id.listView)
        adapter = OfertaAdapter(requireContext(), R.layout.item_oferta_grid, mutableListOf())
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val ofertaSeleccionada = adapter.getItem(position)
            if (ofertaSeleccionada != null) {
                abrirPaginaWeb(ofertaSeleccionada)
            }
        }

        searchView = view.findViewById(R.id.searchView)
        searchView.queryHint = "Busca un país...";
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        lifecycleScope.launch {
            cargarImagenesDesdeMipmap()
        }

        return view
    }

    private suspend fun cargarImagenesDesdeMipmap() {
        logoRyanair = crearDrawableDesdeMipmap(R.mipmap.logo_ryanair)
        logoIberia = crearDrawableDesdeMipmap(R.mipmap.logo_iberia)
        logoAirEuropa = crearDrawableDesdeMipmap(R.mipmap.logo_aireuropa)

        val ofertasList: MutableList<Oferta> = mutableListOf(
            Oferta("https://www.ryanair.com/flights/es/es", logoRyanair, "Nueva York, Estados Unidos", generarPrecio()),
            Oferta("https://www.iberia.com/es/", logoIberia, "París, Francia", generarPrecio()),
            Oferta("https://www.iberia.com/es/", logoIberia, "Tokio, Japón", generarPrecio()),
            Oferta("https://www.aireuropa.com/es/es/home", logoAirEuropa, "Roma, Italia", generarPrecio()),
            Oferta("https://www.aireuropa.com/es/es/home", logoAirEuropa, "Sídney, Australia", generarPrecio()),
            Oferta("https://www.ryanair.com/flights/es/es", logoRyanair, "Londres, Reino Unido", generarPrecio()),
            Oferta("https://www.iberia.com/es/", logoIberia, "Río de Janeiro, Brasil", generarPrecio()),
            Oferta("https://www.ryanair.com/flights/es/es", logoRyanair, "Dubái, Emiratos Árabes Unidos", generarPrecio()),
            Oferta("https://www.iberia.com/es/", logoIberia, "Los Ángeles, Estados Unidos", generarPrecio()),
            Oferta("https://www.iberia.com/es/", logoIberia, "Moscú, Rusia", generarPrecio()),
            Oferta("https://www.aireuropa.com/es/es/home", logoAirEuropa, "Cancún, México", generarPrecio()),
            Oferta("https://www.aireuropa.com/es/es/home", logoAirEuropa, "Hong Kong", generarPrecio()),
            Oferta("https://www.iberia.com/es/", logoIberia, "Barcelona, España", generarPrecio()),
            Oferta("https://www.iberia.com/es/", logoIberia, "Berlín, Alemania", generarPrecio()),
            Oferta("https://www.aireuropa.com/es/es/home", logoAirEuropa, "Toronto, Canadá", generarPrecio()),
            Oferta("https://www.ryanair.com/flights/es/es", logoRyanair, "Seúl, Corea del Sur", generarPrecio()),
            Oferta("https://www.ryanair.com/flights/es/es", logoRyanair, "Bangkok, Tailandia", generarPrecio()),
            Oferta("https://www.aireuropa.com/es/es/home", logoAirEuropa, "Buenos Aires, Argentina", generarPrecio()),
            Oferta("https://www.aireuropa.com/es/es/home", logoAirEuropa, "Ámsterdam, Países Bajos", generarPrecio()),
            Oferta("https://www.ryanair.com/flights/es/es", logoRyanair, "Estambul, Turquía", generarPrecio())
        )

        adapter.clear()
        adapter.addAll(ofertasList)
        adapter.notifyDataSetChanged()
    }

    private suspend fun crearDrawableDesdeMipmap(resourceId: Int): Drawable? {
        return try {
            val bitmap: Bitmap = BitmapFactory.decodeResource(resources, resourceId)

            BitmapDrawable(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun abrirPaginaWeb(oferta: Oferta) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(oferta.enlaceRedireccion)
        startActivity(intent)
    }

    private fun generarPrecio(): Int {
        return Random.nextInt(400, 1500)
    }

    class OfertaAdapter(context: Context, resource: Int, objects: List<Oferta>) :
        ArrayAdapter<Oferta>(context, resource, objects) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val itemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_oferta_grid, parent, false)

            val oferta = getItem(position)

            val imageViewLogo = itemView.findViewById<ImageView>(R.id.imageView_logo)
            val textViewDestino = itemView.findViewById<TextView>(R.id.textView_destino)
            val textViewPrecio = itemView.findViewById<TextView>(R.id.textView_precio)

            oferta?.let {
                imageViewLogo.setImageDrawable(it.aerolineaFoto)
                textViewDestino.text = it.destino
                textViewPrecio.text = "${it.precio}€"
            }

            return itemView
        }
    }
}
