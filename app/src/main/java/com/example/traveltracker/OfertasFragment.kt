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
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.traveltracker.Oferta
import com.example.traveltracker.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL
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

        // Configurar el ListView y el adaptador
        listView = view.findViewById(R.id.listView)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, mutableListOf<Oferta>())
        listView.adapter = adapter

        // Manejar clics en elementos del ListView
        listView.setOnItemClickListener { _, _, position, _ ->
            // Aquí puedes manejar el clic en el elemento
            val ofertaSeleccionada = adapter.getItem(position)
            if (ofertaSeleccionada != null) {
                abrirPaginaWeb(ofertaSeleccionada)
            }
        }

        // Configurar el SearchView
        searchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        // Iniciar las corrutinas aquí para cargar las imágenes
        lifecycleScope.launch {
            cargarImagenes()
        }

        return view
    }

    private suspend fun cargarImagenes() {
        // Crear objetos de imagen para los logotipos de aerolíneas
        logoRyanair = crearDrawableDesdeUrl("https://upload.wikimedia.org/wikipedia/commons/9/9d/Arpa_Ryanair.png")
        logoIberia = crearDrawableDesdeUrl("https://grupo.iberia.com/contents/archives/475/109/images/thumb255x185_auto/iberia_47510965352123_thumb.png")
        logoAirEuropa = crearDrawableDesdeUrl("https://www.bradutch.com/wp-content/uploads/2020/01/air-europa-logo-0.png")

        // Crear lista de ofertas con imágenes cargadas
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

        // Actualizar el adaptador con la lista de ofertas
        adapter.clear()
        adapter.addAll(ofertasList)
        adapter.notifyDataSetChanged()
    }

    private fun abrirPaginaWeb(oferta: Oferta) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(oferta.enlaceRedireccion)
        startActivity(intent)
    }

    private suspend fun crearDrawableDesdeUrl(url: String): Drawable? {
        return try {
            val inputStream: InputStream = withContext(Dispatchers.IO) {
                URL(url).openStream()
            }

            val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)

            BitmapDrawable(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun generarPrecio(): Int {
        return Random.nextInt(400, 1500)
    }
}

class DrawableAdapter(context: Context, drawables: List<Drawable>) : ArrayAdapter<Drawable>(context, android.R.layout.simple_list_item_1, drawables) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView = ImageView(context)
        imageView.setImageDrawable(getItem(position))
        return imageView
    }
}
