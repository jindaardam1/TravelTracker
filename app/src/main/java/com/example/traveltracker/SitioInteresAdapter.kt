package com.example.traveltracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SitioInteresAdapter(private val sitiosInteres: List<String>) : RecyclerView.Adapter<SitioInteresAdapter.SitioInteresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitioInteresViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sitio_interes, parent, false)
        return SitioInteresViewHolder(view)
    }

    override fun onBindViewHolder(holder: SitioInteresViewHolder, position: Int) {
        val sitioInteres = sitiosInteres[position]
        holder.bind(sitioInteres)
    }

    override fun getItemCount(): Int {
        return sitiosInteres.size
    }

    inner class SitioInteresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreSitioTextView: TextView = itemView.findViewById(R.id.nombreSitioTextView)

        fun bind(sitioInteres: String) {
            nombreSitioTextView.text = sitioInteres
        }
    }
}