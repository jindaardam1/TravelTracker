package Recomendacion

/**
 * Data class que representa un sitio de interés relacionado con una recomendación en la aplicación TravelTracker.
 *
 * @property nombre El nombre del sitio de interés.
 * @property imagenResource El recurso de imagen asociado al sitio de interés.
 * @property enlace El enlace o URL relacionado con el sitio de interés.
 */
data class SitioInteres(val nombre: String, val imagenResource: Int, val enlace: String)
