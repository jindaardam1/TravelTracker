import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.traveltracker.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient

class RecomendacionFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var placesClient: PlacesClient
    private lateinit var locationTextView: TextView

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recomendacion, container, false)

        locationTextView = view.findViewById(R.id.Nombre)

        // Initialize Places
        Places.initialize(requireContext(), getString(R.string.google_maps_key))
        placesClient = Places.createClient(requireContext())

        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        // Check for location permission
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is already granted, get the last known location
            getLocation()
        } else {
            // Request location permission
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }

        return view
    }

    private fun getLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                // Use the location to fetch the current place
                fetchCurrentPlace(location.latitude, location.longitude)
            }
        }
    }

    private fun fetchCurrentPlace(latitude: Double, longitude: Double) {
        // Define the fields to specify which types of place data to return
        val placeFields = listOf(Place.Field.NAME)

        // Use the builder to create a FindCurrentPlaceRequest
        val request = com.google.android.libraries.places.api.net.FindCurrentPlaceRequest.newInstance(placeFields)

        // Call findCurrentPlace and handle the response
        val placeResponse = placesClient.findCurrentPlace(request)
        placeResponse.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val likelyPlaces = task.result
                if (likelyPlaces != null && likelyPlaces.placeLikelihoods.isNotEmpty()) {
                    // Get the most likely place
                    val currentPlace = likelyPlaces.placeLikelihoods[0].place
                    locationTextView.text = currentPlace.name
                }
            }
        }
    }
}
