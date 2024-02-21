package model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import model.dao.DAO
import model.entity.Placeholder

@Database(entities = [Placeholder::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun userDao(): DAO

    companion object {
        private const val DB = "TravelTracker.db"

        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDatabase::class.java,
                        DB).build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}