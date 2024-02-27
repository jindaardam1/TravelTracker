package com.example.traveltracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltracker.Country
import com.example.traveltracker.R
import java.util.Locale


class CountryAdapter(private val countries: List<com.example.traveltracker.Country>) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>(), Filterable {

    private var filteredCountries: List<Country> = countries

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
        val country = filteredCountries[position]
        holder.nameTextView.text = country.name
        holder.flagEmojiTextView.text = country.flagEmoji
    }


    override fun getItemCount(): Int {
        return filteredCountries.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<com.example.traveltracker.Country>()
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

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                filteredCountries = results?.values as List<com.example.traveltracker.Country>
                notifyDataSetChanged()
            }
        }
    }
}