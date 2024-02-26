package com.example.traveltracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.Locale

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MapaFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mapa, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val searchView: SearchView = view.findViewById(R.id.searchView)
        setupRecyclerView(recyclerView, searchView)
        return view
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, searchView: SearchView) {
        val countries = listOf("Mexico", "Canada", "United States", "United Kingdom", "Germany")
        Log.d("MapaFragment", "Países en la lista: $countries")
        val adapter = CountryAdapter(countries)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("MapaFragment", "Texto de búsqueda cambiado: $newText")
                adapter.getFilter().filter(newText)
                return true
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private class CountryAdapter(private val countries: List<String>) :
        RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

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
            holder.countryName.text = filteredCountries[position]
        }

        override fun getItemCount(): Int {
            return filteredCountries.size
        }

        fun getFilter(): Filter {
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

                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                    filteredCountries = results?.values as? List<String> ?: listOf()
                    notifyDataSetChanged()
                    Log.d("CountryAdapter", "Países filtrados: $filteredCountries")
                }
            }
        }
    }
}
