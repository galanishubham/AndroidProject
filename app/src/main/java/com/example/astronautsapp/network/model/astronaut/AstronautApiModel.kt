package com.example.astronautsapp.network.model.astronaut

data class AstronautApiModel(
    val age: Int,
    val agency: Agency,
    val bio: String,
    val date_of_birth: String,
    val date_of_death: Any,
    val first_flight: String,
    val flights_count: Int,
    val id: Int,
    val in_space: Boolean,
    val instagram: String,
    val landings_count: Int,
    val last_flight: String,
    val name: String,
    val nationality: String,
    val profile_image: String,
    val profile_image_thumbnail: String,
    val status: Status,
    val twitter: String,
    val type: Type,
    val url: String,
    val wiki: String
)

data class Agency(
    val abbrev: String,
    val administrator: String,
    val country_code: String,
    val description: String,
    val featured: Boolean,
    val founding_year: String,
    val id: Int,
    val image_url: Any,
    val launchers: String,
    val logo_url: String,
    val name: String,
    val parent: Any,
    val spacecraft: String,
    val type: String,
    val url: String
)

data class Type(
    val id: Int,
    val name: String
)

data class Status(
    val id: Int,
    val name: String
)