package model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estado_paises")
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
