package core

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Localizacion {
    private val urlBase =
        "https://eu1.locationiq.com/v1/reverse?key=pk.4f19789516687fb1486834896e17c95b&lat=%s&lon=%s&format=json"

    private fun construirURL(latitud: Double, longitud: Double): String {
        return urlBase.format(latitud, longitud)
    }

    fun coordenadasAPais(latitud: Double, longitud: Double): String {
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

                return country ?: "País no encontrado"
            } else {
                return "Error al realizar la solicitud HTTP"
            }
        } catch (e: Exception) {
            return e.toString()
        }
    }

    private fun obtenerPaisDesdeJSON(json: String): String? {
        val jsonObject = JSONObject(json)
        val addressObject = jsonObject.getJSONObject("address")
        return addressObject.getString("country_code")
    }
}