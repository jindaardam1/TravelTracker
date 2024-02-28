import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.traveltracker.R

class RecomendacionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recomendacion, container, false)

        // Referenciar vistas
        val nombreTextView: TextView = view.findViewById(R.id.Nombre)
        val imageView: ImageView = view.findViewById(R.id.imageView3)

        // Introducir nombre y url de la imagen
        val nombreRecomendacion = "PLAYA DE VARADERO (CUBA)"
        val imagenURL = "https://media.traveler.es/photos/61376e02c6202df7591601a1/master/w_1920%2Cc_limit/138178.jpg"

        // Asignar el nombre a TextView
        nombreTextView.text = nombreRecomendacion

        // Cargar imagen desde la URL usando Glide
        Glide.with(requireContext())
            .load(imagenURL)
            .into(imageView)



        return view
    }
}
