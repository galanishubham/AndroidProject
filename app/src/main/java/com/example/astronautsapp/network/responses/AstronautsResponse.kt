package com.example.astronautsapp.network.responses

import com.example.astronautsapp.network.model.astronaut.AstronautApiModel
import com.google.gson.annotations.SerializedName

// POJO for GET astronauts response
data class AstronautsResponse (
    var count: Int,

    @SerializedName("results")
    var astronauts: List<AstronautApiModel>
)