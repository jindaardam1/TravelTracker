package model.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Clase de entidad que representa un país confirmado en la aplicación TravelTracker.
 *
 * Esta entidad se utiliza para almacenar la información de países que el usuario ha confirmado haber visitado.
 *
 * @property codConfirmado Identificador único generado automáticamente para la entidad.
 * @property foto Datos de la foto asociada al país confirmado en formato de arreglo de bytes.
 * @property codPais Código del país asociado al país confirmado.
 */
@Entity(tableName = "paises_confirmados",
    foreignKeys = [ForeignKey(
        entity = EstadoPais::class,
        parentColumns = ["nombre_pais"],
        childColumns = ["cod_pais"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class PaisConfirmado(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cod_confirmado")
    val codConfirmado: Int = 0,

    @ColumnInfo(name = "foto")
    val foto: ByteArray?,

    @ColumnInfo(name = "cod_pais")
    val codPais: String
)