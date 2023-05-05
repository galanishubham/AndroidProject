package com.example.astronautsapp.data

import android.content.Context
import com.example.astronautsapp.data.room.AstronautsRoomDatabase
import com.example.astronautsapp.domain.model.Astronaut
import com.example.astronautsapp.domain.model.AstronautDetails
import com.example.astronautsapp.network.AstronautsAPIService
import com.example.astronautsapp.network.model.astronaut.AstronautDataMapper
import com.example.astronautsapp.network.model.astronaut_details.AstronautDetailsDataMapper
import com.example.astronautsapp.util.InternetConnection

interface AstronautsRepository {
    // Repository fetch astronaut list from astronautAPI
    suspend fun getAstronauts(): List<Astronaut>
    suspend fun getAstronautDetails(id: Int): AstronautDetails

}

// implementing AstronautsRepository
class DefaultAstronautsRepository(
    private val astronautService: AstronautsAPIService,
    private val astronautDatabase: AstronautsRoomDatabase,
    private val context: Context,
): AstronautsRepository {

    override suspend fun getAstronauts(): List<Astronaut> {

       var result: List<Astronaut> = if(InternetConnection.isOnline(context)) {
            // function returns astronauts list data from retrofit service
            val response = AstronautDataMapper().fromDataList(astronautService.getAstronauts().astronauts);
            // save to database
            astronautDatabase.astronautsDao().insertAstronauts(response)
            response
        }else {
            astronautDatabase.astronautsDao().getAstronauts()
        }

        return result

    }

    override suspend fun getAstronautDetails(id: Int): AstronautDetails {
        // function returns astronaut details data from retrofit service
        return AstronautDetailsDataMapper().mapToDomainModel(astronautService.getAstronautDetails(id))

    }

}