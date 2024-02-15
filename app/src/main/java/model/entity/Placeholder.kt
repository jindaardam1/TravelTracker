package model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Placeholder(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    @ColumnInfo(name = "NOMBRE") val nombre: String,
    @ColumnInfo(name = "EDAD") val edad: Int,
    @ColumnInfo(name = "FECHA") val fecha: String
)
