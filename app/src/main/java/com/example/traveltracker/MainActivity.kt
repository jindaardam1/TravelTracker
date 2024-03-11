package com.example.traveltracker

import OfertasFragment
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseApp
import model.local.database.LocalDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var myImageView: ImageView
    private lateinit var navigation_option1: ImageView
    private lateinit var navigation_option2: ImageView
    private lateinit var navigation_option3: ImageView
    private lateinit var navigation_option4: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)

        try {
            var db = LocalDatabase.getInstance(this)
            Log.i("Database", "Base de datos creada con éxito")
            db.estadoPaisDao().getAll()
        } catch (e: Exception) {
            Log.e("Error", e.toString())
        }

        myImageView = findViewById(R.id.navigation_option1) // Cambiar al ID correcto si es diferente
        navigation_option1 = findViewById(R.id.navigation_option1)
        navigation_option2 = findViewById(R.id.navigation_option2)
        navigation_option3 = findViewById(R.id.navigation_option3)
        navigation_option4 = findViewById(R.id.navigation_option4)

        changeColor(navigation_option1, R.color.lightblue)

        navigation_option1.setOnClickListener {
            changeColor(navigation_option1, R.color.lightblue)
            loadFragment(MapaFragment())
        }
        navigation_option2.setOnClickListener {
            changeColor(navigation_option2, R.color.lightblue)
            loadFragment(OfertasFragment())
        }
        navigation_option3.setOnClickListener {
            changeColor(navigation_option3, R.color.lightblue)
            loadFragment(RecomendacionFragment())
        }
        navigation_option4.setOnClickListener {
            changeColor(navigation_option4, R.color.lightblue)
            val username = intent.getStringExtra("username")
            val perfilFragment = PerfilFragment()
            val bundle = Bundle()
            bundle.putString("username", username)
            perfilFragment.arguments = bundle
            loadFragment(perfilFragment)
        }

        loadFragment(MapaFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun changeColor(imageView: ImageView, colorResourceId: Int) {
        navigation_option1.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.SRC_IN)
        navigation_option2.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.SRC_IN)
        navigation_option3.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.SRC_IN)
        navigation_option4.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.SRC_IN)

        imageView.setColorFilter(resources.getColor(colorResourceId), PorterDuff.Mode.SRC_IN)
    }
}
