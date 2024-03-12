package Recomendacion

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltracker.R

/**
 * Adaptador personalizado para la lista de sitios de interés en el fragmento de recomendaciones de la aplicación TravelTracker.
 *
 * @property context El contexto de la aplicación.
 * @property sitiosInteres La lista de sitios de interés que se mostrarán en el adaptador.
 */
class SitioInteresAdapter(private val context: Context, private val sitiosInteres: List<SitioInteres>) :
    RecyclerView.Adapter<SitioInteresAdapter.SitioInteresViewHolder>() {

    /**
     * Clase interna que representa una vista de elemento de sitio de interés en el RecyclerView.
     *
     * @param itemView La vista del elemento de sitio de interés.
     */
    inner class SitioInteresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageSitio: ImageView = itemView.findViewById(R.id.imageSitioInteres)
        val textNombreSitio: TextView = itemView.findViewById(R.id.textNombreSitio)
    }

    /**
     * Crea y devuelve una nueva instancia de [SitioInteresViewHolder].
     *
     * @param parent El grupo de vistas en el que se inflará la vista del elemento.
     * @param viewType El tipo de vista del nuevo elemento.
     * @return La instancia de [SitioInteresViewHolder].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitioInteresViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_sitio_interes, parent, false)
        return SitioInteresViewHolder(itemView)
    }

    /**
     * Vincula los datos de un sitio de interés a la vista del elemento en una posición específica.
     *
     * @param holder El [SitioInteresViewHolder] que representa la vista del elemento.
     * @param position La posición del elemento en la lista.
     */
    override fun onBindViewHolder(holder: SitioInteresViewHolder, position: Int) {
        val currentItem = sitiosInteres[position]

        holder.imageSitio.setImageResource(currentItem.imagenResource)

        holder.textNombreSitio.text = currentItem.nombre

        holder.itemView.setOnClickListener {
            abrirEnlaceWeb(currentItem.enlace)
        }
    }

    /**
     * Devuelve la cantidad total de elementos en la lista de sitios de interés.
     *
     * @return El tamaño de la lista de sitios de interés.
     */
    override fun getItemCount() = sitiosInteres.size

    /**
     * Abre un enlace web utilizando un intent implícito.
     *
     * @param enlace El enlace o URL a abrir.
     */
    private fun abrirEnlaceWeb(enlace: String) {
        val uri = Uri.parse(enlace)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }
}

