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

/*
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltracker.R
import java.util.*

// Declaración de la clase Oferta
data class Oferta(val nombre: String, val descripcion: String)

class OfertasFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: OfertasAdapter

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

        // Configurar el RecyclerView y el adaptador
        recyclerView = view.findViewById(R.id.rvOfertas)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = OfertasAdapter(ofertasList)
        recyclerView.adapter = adapter

        // Manejar clics en elementos del RecyclerView
        adapter.setOnItemClickListener { oferta ->
            // Aquí puedes manejar el clic en el elemento
            abrirPaginaWeb(oferta)
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

    // Definición del adaptador
    private class OfertasAdapter(private val ofertasList: List<Oferta>) :
        RecyclerView.Adapter<OfertasAdapter.OfertaViewHolder>(), Filterable {

        private var onItemClick: ((Oferta) -> Unit)? = null
        private var filteredOfertasList: List<Oferta> = ofertasList.toMutableList()

        inner class OfertaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
            private val descripcionTextView: TextView = itemView.findViewById(R.id.descripcionTextView)

            init {
                itemView.setOnClickListener {
                    onItemClick?.invoke(filteredOfertasList[adapterPosition])
                }
            }

            fun bind(oferta: Oferta) {
                nombreTextView.text = oferta.nombre
                descripcionTextView.text = oferta.descripcion
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfertaViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_oferta, parent, false)
            return OfertaViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: OfertaViewHolder, position: Int) {
            holder.bind(filteredOfertasList[position])
        }

        override fun getItemCount(): Int {
            return filteredOfertasList.size
        }

        fun setOnItemClickListener(listener: (Oferta) -> Unit) {
            onItemClick = listener
        }

        override fun getFilter(): Filter {
            return object : Filter() {
                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    val charString = constraint.toString().toLowerCase(Locale.getDefault())
                    filteredOfertasList = if (charString.isEmpty()) {
                        ofertasList.toMutableList()
                    } else {
                        ofertasList.filter { it.nombre.toLowerCase(Locale.getDefault()).contains(charString) }
                    }

                    val filterResults = FilterResults()
                    filterResults.values = filteredOfertasList
                    return filterResults
                }

                @Suppress("UNCHECKED_CAST")
                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                    filteredOfertasList = results?.values as List<Oferta>
                    notifyDataSetChanged()
                }
            }
        }
    }
}

*/
