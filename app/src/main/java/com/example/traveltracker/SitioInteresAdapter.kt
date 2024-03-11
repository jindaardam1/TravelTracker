package com.example.traveltracker

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

class SitioInteresAdapter(private val context: Context, private val sitiosInteres: List<SitioInteres>) :
    RecyclerView.Adapter<SitioInteresAdapter.SitioInteresViewHolder>() {

    inner class SitioInteresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageSitio: ImageView = itemView.findViewById(R.id.imageSitioInteres)
        val textNombreSitio: TextView = itemView.findViewById(R.id.textNombreSitio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitioInteresViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_sitio_interes, parent, false)
        return SitioInteresViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SitioInteresViewHolder, position: Int) {
        val currentItem = sitiosInteres[position]

        holder.imageSitio.setImageResource(currentItem.imagenResource)

        holder.textNombreSitio.text = currentItem.nombre

        holder.itemView.setOnClickListener {
            abrirEnlaceWeb(currentItem.enlace)
        }
    }
    override fun getItemCount() = sitiosInteres.size

    private fun abrirEnlaceWeb(enlace: String) {
        val uri = Uri.parse(enlace)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }
}

