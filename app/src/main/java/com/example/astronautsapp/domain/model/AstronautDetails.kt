package com.example.astronautsapp.domain.model

data class AstronautDetails(
    val name: String,
    val profileImg: String,
    val flights: List<Flight>
)

data class Flight (
    val name: String
    )
