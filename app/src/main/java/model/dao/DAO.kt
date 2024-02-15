package model.dao

import androidx.room.Dao
import androidx.room.Query
import model.entity.Placeholder

@Dao
interface DAO {
    @Query("SELECT * FROM placeholder")
    fun getAll(): List<Placeholder>
}