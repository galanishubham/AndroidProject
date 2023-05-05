package com.example.astronautsapp.domain.model

data class AstronautDetails (
    val id: Int,
    val name: String,
    val profileImg: String,
    val flights: List<Flight>
)

data class Flight(
    val flightId: String,
    val flightName: String
)


