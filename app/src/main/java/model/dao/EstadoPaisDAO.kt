package model.dao

import EstadoPais
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface EstadoPaisDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(estadoPais: EstadoPais)
}