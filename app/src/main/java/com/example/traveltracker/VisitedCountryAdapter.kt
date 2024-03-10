import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltracker.Country
import com.example.traveltracker.R

class VisitedCountriesAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<VisitedCountriesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val flagEmojiTextView: TextView = view.findViewById(R.id.flagEmojiTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.nameTextView.text = country.name
        holder.flagEmojiTextView.text = country.flagEmoji
    }

    override fun getItemCount(): Int {
        return countries.size
    }
}
