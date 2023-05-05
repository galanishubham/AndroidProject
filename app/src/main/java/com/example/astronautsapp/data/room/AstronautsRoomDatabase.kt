package com.example.astronautsapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.astronautsapp.domain.model.Astronaut

@Database(entities = [Astronaut::class], version = 1)
abstract  class AstronautsRoomDatabase: RoomDatabase() {
    abstract fun astronautsDao(): AstronautsDao

    companion object {
        @Volatile
        private var INSTANCE: AstronautsRoomDatabase? = null

        fun getDatabase(context: Context): AstronautsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AstronautsRoomDatabase::class.java,
                    "astronauts_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
