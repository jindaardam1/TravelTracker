package Mapa

/**
 * Clase de datos que representa la información hash de un país en el mapa interactivo.
 *
 * @property color El color asociado al país en formato hexadecimal.
 * @property nombre El nombre del país.
 * @property data Los datos de la ruta del país en el mapa.
 */
data class MapaHash(
    var color: String,
    val nombre: String,
    val data: String
)

