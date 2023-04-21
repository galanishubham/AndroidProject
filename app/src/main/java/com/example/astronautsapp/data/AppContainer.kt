package com.example.astronautsapp.data

import com.example.astronautsapp.network.AstronautsAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Dependency Injection container at the application level
interface AppContainer {
    val astronautsRepository: AstronautsRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://ll.thespacedevs.com/2.2.0/"

    // use retrofit builder to build retrofit object
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService : AstronautsAPIService by lazy {
        // will return retrofit api service object
        retrofit.create(AstronautsAPIService::class.java)
    }

    // DI implementation for Astronaut Repository
    override val astronautsRepository: AstronautsRepository by lazy {
        DefaultAstronautsRepository(retrofitService)
    }
}