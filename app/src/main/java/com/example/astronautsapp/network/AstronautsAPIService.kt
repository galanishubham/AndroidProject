package com.example.astronautsapp.network

import com.example.astronautsapp.network.model.astronaut_details.AstronautDetailsApiModel
import com.example.astronautsapp.network.responses.AstronautsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AstronautsAPIService {
    // get list of astronauts
    @GET("astronaut")
    suspend fun getAstronauts(): AstronautsResponse

    // astronaut detail api
    @GET("astronaut/{id}")
    suspend fun getAstronautDetails(
        @Path("id") id: Int
    ): AstronautDetailsApiModel
}
