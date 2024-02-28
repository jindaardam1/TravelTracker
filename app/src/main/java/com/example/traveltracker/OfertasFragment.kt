import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.traveltracker.R

class OfertasFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var searchView: SearchView
    private lateinit var adapter: ArrayAdapter<String>
    private var ofertasList = listOf(
        "Vuelo por 600€ hacia Bolivia",
        "Vuelo por 320€ hacia Noruega",
        "Vuelo por 1000€ hacia Australia",
        "Vuelo por 1000€ hacia Australia",
        "Vuelo por 600€ hacia Bolivia",
        "Vuelo por 320€ hacia Noruega",
        "Vuelo por 1000€ hacia Australia",
        "Vuelo por 1000€ hacia Australia",
        "Vuelo por 600€ hacia Bolivia",
        "Vuelo por 320€ hacia Noruega",
        "Vuelo por 1000€ hacia Australia",
        "Vuelo por 1000€ hacia Australia",
        "Vuelo por 600€ hacia Bolivia",
        "Vuelo por 320€ hacia Noruega",
        "Vuelo por 1000€ hacia Australia",
        "Vuelo por 1000€ hacia Australia"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ofertas, container, false)

        // Meter valores al ListView
        listView = view.findViewById(R.id.listView)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, ofertasList)
        listView.adapter = adapter

        // Clickamos en una oferta y abrimos la página web de la oferta
        listView.setOnItemClickListener { _, _, position, _ ->
            // Open an empty HTML page
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.iberia.com/es/buscador-vuelos/")
            startActivity(intent)
        }


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
}
