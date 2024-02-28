package model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import model.local.entity.EstadoPais

@Dao
interface EstadoPaisDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(estadoPais: EstadoPais)

    @Query("SELECT * FROM estado_paises")
    fun getAll(): List<EstadoPais>

    @Query("UPDATE estado_paises SET visitando = :visitando WHERE nombre_pais = :nombrePais")
    suspend fun updateVisitando(nombrePais: String, visitando: Boolean)

    @Query("UPDATE estado_paises SET ha_estado = :haEstado WHERE nombre_pais = :nombrePais")
    suspend fun updateHaEstado(nombrePais: String, haEstado: Boolean)
}