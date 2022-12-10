package com.example.lugares.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lugares.model.Propiedad

@Database(entities = [Propiedad::class], version = 1, exportSchema = false)
abstract class PropiedadDatabase : RoomDatabase() {
    abstract fun propiedadDao() : PropiedadDao

    companion object {
        @Volatile
        private var INSTANCE: PropiedadDatabase? = null

        fun getDatabase(context: android.content.Context) : PropiedadDatabase {
            val bd = INSTANCE
            if (bd != null) {
                return  bd
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PropiedadDatabase::class.java,
                    "propiedad_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}