package model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import model.entity.EstadoPais

@Dao
interface EstadoPaisDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(estadoPais: EstadoPais)

    @Query("SELECT * FROM estado_paises")
    fun getAll(): List<EstadoPais>
}