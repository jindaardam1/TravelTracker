package utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Clase utilitaria para convertir coordenadas geográficas (latitud y longitud) en un nombre de país utilizando
 * el servicio de geolocalización de LocationIQ.
 *
 * @property apiKey La clave API necesaria para realizar solicitudes a LocationIQ.
 * @property urlBase La URL base del servicio de geolocalización.
 */
class Localizacion {
    private val apiKey = "pk.4f19789516687fb1486834896e17c95b"
    private val urlBase =
        "https://eu1.locationiq.com/v1/reverse?key=${apiKey}&lat=%s&lon=%s&format=json"

    /**
     * Construye la URL completa para la solicitud de geolocalización.
     *
     * @param latitud La latitud de las coordenadas geográficas.
     * @param longitud La longitud de las coordenadas geográficas.
     * @return La URL completa para la solicitud de geolocalización.
     */
    private fun construirURL(latitud: Double, longitud: Double): String {
        return urlBase.format(latitud, longitud)
    }

    /**
     * Convierte las coordenadas geográficas en un nombre de país utilizando el servicio de geolocalización de LocationIQ.
     *
     * @param latitud La latitud de las coordenadas geográficas.
     * @param longitud La longitud de las coordenadas geográficas.
     * @return El nombre del país correspondiente a las coordenadas proporcionadas.
     */
    suspend fun coordenadasAPais(latitud: Double, longitud: Double): String {
        return withContext(Dispatchers.IO) {
            try {
                val url = construirURL(latitud, longitud)

                val connection = URL(url).openConnection() as HttpURLConnection
                connection.requestMethod = "GET"

                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    val response = StringBuilder()
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        response.append(line)
                    }
                    reader.close()

                    val json = response.toString()
                    val country = obtenerPaisDesdeJSON(json)

                    country ?: "País no encontrado"
                } else {
                    "Error al realizar la solicitud HTTP"
                }
            } catch (e: Exception) {
                e.toString()
            }
        }
    }

    /**
     * Extrae el nombre del país desde la respuesta JSON proporcionada por el servicio de geolocalización.
     *
     * @param json La respuesta JSON del servicio de geolocalización.
     * @return El nombre del país o nulo si no se encuentra.
     */
    private fun obtenerPaisDesdeJSON(json: String): String? {
        val jsonObject = JSONObject(json)
        val addressObject = jsonObject.getJSONObject("address")
        return addressObject.getString("country_code")
    }
}