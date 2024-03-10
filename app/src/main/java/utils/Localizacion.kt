package utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Localizacion {
    private val apiKey = "pk.4f19789516687fb1486834896e17c95b"
    private val urlBase =
        "https://eu1.locationiq.com/v1/reverse?key=${apiKey}&lat=%s&lon=%s&format=json"

    private fun construirURL(latitud: Double, longitud: Double): String {
        return urlBase.format(latitud, longitud)
    }

    // Este método puede ser suspendido y llamado desde un contexto de coroutine
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

    private fun obtenerPaisDesdeJSON(json: String): String? {
        val jsonObject = JSONObject(json)
        val addressObject = jsonObject.getJSONObject("address")
        return addressObject.getString("country_code")
    }
}