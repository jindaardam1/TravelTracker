package Mapa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltracker.R
import java.util.Locale

/**
 * Adaptador para la lista de países en el mapa interactivo.
 *
 * @property countries Lista de países.
 * @property onItemClickListener Función de devolución de llamada para manejar clics en elementos.
 */
    class CountryAdapter(private val countries: List<Country>, private val onItemClickListener: (String) -> Unit) :
        RecyclerView.Adapter<CountryAdapter.ViewHolder>(), Filterable {

    private var filteredCountries: List<Country> = countries
    private var selectedItemPosition: Int = RecyclerView.NO_POSITION

    /**
     * Clase interna que representa la vista de un elemento en el adaptador.
     *
     * @param view La vista del elemento.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val flagEmojiTextView: TextView = view.findViewById(R.id.flagEmojiTextView)
    }

    /**
     * Crea y devuelve un ViewHolder asociado a la vista del elemento.
     *
     * @param parent El grupo de vista principal al que pertenece la vista del elemento.
     * @param viewType El tipo de vista del elemento.
     * @return ViewHolder creado.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return ViewHolder(view)
    }

    /**
     * Vincula los datos en la posición dada con los elementos de la vista.
     *
     * @param holder El ViewHolder que mantiene la vista del elemento.
     * @param position La posición del elemento en los datos.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = filteredCountries[position]
        holder.nameTextView.text = country.name
        holder.flagEmojiTextView.text = country.flagEmoji

        if (position == selectedItemPosition) {
            holder.itemView.setBackgroundResource(R.color.selectedItemBackground)
        } else {
            holder.itemView.setBackgroundResource(android.R.color.transparent)
            selectedItemPosition = -1
        }

        holder.itemView.setOnClickListener {
            selectedItemPosition = holder.adapterPosition
            notifyDataSetChanged()
            onItemClickListener(country.id)
        }
    }

    /**
     * Devuelve la cantidad total de elementos en el conjunto de datos.
     *
     * @return El tamaño del conjunto de datos.
     */
    override fun getItemCount(): Int {
        return filteredCountries.size
    }

    /**
     * Devuelve un objeto Filter que se utiliza para realizar filtrados en el adaptador.
     *
     * @return Objeto Filter.
     */
    override fun getFilter(): Filter {
        return object : Filter() {
            /**
             * Realiza el filtrado en segundo plano.
             *
             * @param constraint La restricción de filtrado.
             * @return Resultados del filtrado.
             */
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<Country>()
                val filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim()

                for (country in countries) {
                    if (country.name.toLowerCase(Locale.getDefault()).contains(filterPattern)) {
                        filteredList.add(country)
                    }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            /**
             * Publica los resultados del filtrado en el hilo principal.
             *
             * @param constraint La restricción de filtrado.
             * @param results Resultados del filtrado.
             */
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                filteredCountries = results?.values as List<Country>
                // Limpiar la selección al filtrar los resultados
                selectedItemPosition = RecyclerView.NO_POSITION
                notifyDataSetChanged()
            }
        }


    }
}