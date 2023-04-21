package com.example.astronautsapp.data

import com.example.astronautsapp.domain.model.Astronaut
import com.example.astronautsapp.domain.model.AstronautDetails
import com.example.astronautsapp.network.AstronautsAPIService
import com.example.astronautsapp.network.model.astronaut.AstronautDataMapper
import com.example.astronautsapp.network.model.astronaut_details.AstronautDetailsDataMapper

interface AstronautsRepository {
    // Repository fetch astronaut list from astronautAPI
    suspend fun getAstronauts(): List<Astronaut>
    suspend fun getAstronautDetails(id: Int): AstronautDetails

}

// implementing AstronautsRepository
class DefaultAstronautsRepository(
    private val astronautService: AstronautsAPIService,
): AstronautsRepository {

    override suspend fun getAstronauts(): List<Astronaut> {

        // function returns astronauts list data from retrofit service
        return AstronautDataMapper().fromDataList(astronautService.getAstronauts().astronauts)
    }

    override suspend fun getAstronautDetails(id: Int): AstronautDetails {
        // function returns astronaut details data from retrofit service
        return AstronautDetailsDataMapper().mapToDomainModel(astronautService.getAstronautDetails(id))
    }

}