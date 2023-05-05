package com.example.astronautsapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.astronautsapp.domain.model.Astronaut

@Dao
interface AstronautsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAstronauts(astronaut: List<Astronaut>)

    @Query("SELECT * FROM astronauts_table")
    suspend fun getAstronauts(): List<Astronaut>

}
