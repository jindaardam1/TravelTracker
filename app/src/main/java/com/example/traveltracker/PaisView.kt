package com.example.traveltracker

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class PaisView(private val countries: List<String>) :
    RecyclerView.Adapter<PaisView.ViewHolder>(), Filterable {
    private var filteredCountries: List<String> = countries.toList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryName: TextView = itemView.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.countryName.text = countries[position]
    }

    override fun getItemCount(): Int {
        return filteredCountries.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val filteredList = mutableListOf<String>()
                val searchQuery = constraint.toString().toLowerCase(Locale.getDefault())
                if (!constraint.isNullOrEmpty()) {
                    for (country in countries) {
                        if (country.toLowerCase(Locale.getDefault()).contains(searchQuery)) {
                            filteredList.add(country)
                        }
                    }
                } else {
                    filteredList.addAll(countries)
                }
                filterResults.values = filteredList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredCountries = results?.values as? List<String> ?: listOf()
                notifyDataSetChanged()
            }
        }
    }
}

