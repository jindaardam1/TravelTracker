import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import Mapa.Country
import com.example.traveltracker.MapaFragment
import com.example.traveltracker.R

/**
 * Adaptador para la lista de países visitados en el fragmento del mapa.
 *
 * @property countries Lista de países visitados.
 * @property onItemClickListener Función lambda para gestionar los clics en los elementos del adaptador.
 * @property mapaFragment Instancia del fragmento del mapa al que pertenece el adaptador.
 */
class VisitedCountriesAdapter(
    private val countries: List<Country>,
    private val onItemClickListener: (String) -> Unit,
    private val mapaFragment: MapaFragment
) : RecyclerView.Adapter<VisitedCountriesAdapter.ViewHolder>() {

    private var selectedItemPosition: Int = RecyclerView.NO_POSITION

    /**
     * Clase que representa la vista de un elemento en el adaptador.
     *
     * @property nameTextView TextView para mostrar el nombre del país.
     * @property flagEmojiTextView TextView para mostrar el emoji de la bandera del país.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val flagEmojiTextView: TextView = view.findViewById(R.id.flagEmojiTextView)
    }

    /**
     * Crea y devuelve un ViewHolder para la vista del elemento.
     *
     * @param parent Grupo al que se añadirá la vista del elemento.
     * @param viewType Tipo de vista del elemento.
     * @return Nuevo ViewHolder que contiene la vista del elemento.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)

        return ViewHolder(view)
    }

    /**
     * Vincula los datos de un país con la vista del elemento en el adaptador.
     *
     * @param holder ViewHolder que representa la vista del elemento.
     * @param position Posición del elemento en la lista.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.nameTextView.text = country.name
        holder.flagEmojiTextView.text = country.flagEmoji
        holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, mapaFragment.colorPais))
        holder.itemView.setOnClickListener {
            selectedItemPosition = holder.adapterPosition
            notifyDataSetChanged()
            onItemClickListener(country.id)
        }
    }

    /**
     * Obtiene el número total de elementos en la lista.
     *
     * @return Número total de elementos en la lista.
     */
    override fun getItemCount(): Int {
        return countries.size
    }
}
