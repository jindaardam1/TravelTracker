package model.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Clase de entidad que representa el estado de un país en la aplicación TravelTracker.
 *
 * Esta entidad se utiliza para representar la información relacionada con el estado y visita de un país.
 *
 * @property codPais Identificador único generado automáticamente para la entidad.
 * @property nombrePais Nombre del país.
 * @property haEstado Indica si el usuario ha establecido que ha visitado el país.
 * @property visitando Indica si el usuario está visitando actualmente el país.
 * @property ultimaModificacion Fecha de la última modificación en el estado del país.
 */
@Entity(tableName = "estado_paises",
    indices = [Index(value = ["nombre_pais"], unique = true)])
data class EstadoPais(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cod_pais")
    val codPais: Int = 0,

    @ColumnInfo(name = "nombre_pais")
    val nombrePais: String,

    @ColumnInfo(name = "ha_estado")
    val haEstado: Boolean,

    @ColumnInfo(name = "visitando")
    val visitando: Boolean,

    @ColumnInfo(name = "ultima_modificacion")
    val ultimaModificacion: String
)
