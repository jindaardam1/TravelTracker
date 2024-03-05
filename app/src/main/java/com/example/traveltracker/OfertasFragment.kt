import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.traveltracker.R

// Declaración de la clase Oferta
data class Oferta(val nombre: String, val descripcion: String)

class OfertasFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var searchView: SearchView
    private lateinit var adapter: ArrayAdapter<Oferta>

    private val ofertasList: MutableList<Oferta> = mutableListOf(
        Oferta("Oferta 1", "Descripción de la oferta 1"),
        Oferta("Oferta 2", "Descripción de la oferta 2"),
        Oferta("Oferta 3", "Descripción de la oferta 3")
        // Añade más ofertas según sea necesario
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ofertas, container, false)

        // Configurar el ListView y el adaptador
        listView = view.findViewById(R.id.listView)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, ofertasList)
        listView.adapter = adapter

        // Manejar clics en elementos del ListView
        listView.setOnItemClickListener { _, _, position, _ ->
            // Aquí puedes manejar el clic en el elemento
            val ofertaSeleccionada = ofertasList[position]
            abrirPaginaWeb(ofertaSeleccionada)
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

        return view
    }

    private fun abrirPaginaWeb(oferta: Oferta) {
        // Puedes personalizar esta lógica para abrir la página web según la oferta
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.iberia.com/es/buscador-vuelos/")
        startActivity(intent)
    }
}
