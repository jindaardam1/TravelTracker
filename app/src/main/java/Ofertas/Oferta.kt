package Ofertas

import android.graphics.drawable.Drawable

/**
 * Clase de datos que representa una oferta de viaje en la aplicación TravelTracker.
 *
 * Esta clase contiene información sobre una oferta, como el enlace de redirección, la imagen de la aerolínea,
 * el destino y el precio de la oferta.
 *
 * @property enlaceRedireccion Enlace de redirección asociado a la oferta.
 * @property aerolineaFoto Imagen de la aerolínea representada como un objeto Drawable.
 * @property destino Nombre del destino de la oferta.
 * @property precio Precio de la oferta.
 */
data class Oferta(val enlaceRedireccion: String, val aerolineaFoto: Drawable?, val destino: String, val precio: Int)