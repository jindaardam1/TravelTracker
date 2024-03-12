package controlers


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.local.database.LocalDatabase
import model.local.entity.PaisConfirmado
import utils.ConfirmarPais
import utils.Localizacion

/**
 * Controlador para la funcionalidad de guardar foto y confirmar país.
 *
 * @property context El contexto de la aplicación.
 */
class GuardarFotoController(val context: Context) {

    /**
     * Obtiene la foto en bytes, la ubicación actual y confirma el país en la base de datos.
     */
    fun getPhotoAndSaveOnDb() {
        val fotoEnBytes = getFotoEnBytes()

        val currentLocation = getCurrentLocation()

        val localizacion = Localizacion()

        if (currentLocation != null) {
            val latitud = currentLocation.latitude
            val longitud = currentLocation.longitude

            CoroutineScope(Dispatchers.Main).launch {
                var codigoPais = async {
                    localizacion.coordenadasAPais(latitud, longitud)
                }.await()

                codigoPais = codigoPais.trim().uppercase()

                val miDatabase = LocalDatabase.getInstance(context)
                val paisConfirmadoDAO = miDatabase.paisConfirmadoDAO()

                val paisConfirmado = PaisConfirmado(
                    codConfirmado = 0,
                    foto = fotoEnBytes,
                    codPais = codigoPais
                )

                Log.i("Confirmando país", "Se está intentando confirmar el país $codigoPais")

                withContext(Dispatchers.IO) {
                    paisConfirmadoDAO.insertPaisConfirmado(paisConfirmado)
                }
            }
        }
    }

    /**
     * Obtiene la foto en bytes, mostrando un mensaje de aviso temporal.
     *
     * @return La foto en bytes.
     */
    private fun getFotoEnBytes(): ByteArray? {
        val confirmarPais = ConfirmarPais(context)

        Toast.makeText(context, "Foto para recap en el futuro", Toast.LENGTH_LONG).show()

        confirmarPais.openCamera()

        return null
    }

    /**
     * Obtiene la ubicación actual del dispositivo.
     *
     * @return La ubicación actual del dispositivo.
     */
    private fun getCurrentLocation(): Location? {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val locationProvider = LocationManager.NETWORK_PROVIDER
        var lastKnownLocation: Location? = null
        if (locationManager.isProviderEnabled(locationProvider)) {
            if (ActivityCompat.checkSelfPermission(
                            this.context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this.context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return null
            }
            lastKnownLocation = locationManager.getLastKnownLocation(locationProvider)
        }

        if (lastKnownLocation == null) {
            val gpsProvider = LocationManager.GPS_PROVIDER
            if (locationManager.isProviderEnabled(gpsProvider)) {
                if (ActivityCompat.checkSelfPermission(
                        this.context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this.context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return null
                }
                lastKnownLocation = locationManager.getLastKnownLocation(gpsProvider)
            }
        }

        return lastKnownLocation
    }
}