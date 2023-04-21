package com.example.astronautsapp.network.model.astronaut_details

data class AstronautDetailsApiModel(
    val age: Int,
    val agency: Agency,
    val bio: String,
    val date_of_birth: String,
    val date_of_death: Any,
    val first_flight: String,
    val flights: List<FlightData>,
    val flights_count: Int,
    val id: Int,
    val in_space: Boolean,
    val instagram: String,
    val landings: List<Landing>,
    val landings_count: Int,
    val last_flight: String,
    val name: String,
    val nationality: String,
    val profile_image: String,
    val profile_image_thumbnail: String,
    val status: StatusXX,
    val twitter: String,
    val type: TypeX,
    val url: String,
    val wiki: String
)

data class Agency(
    val id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class Configuration (
    val family: String,
    val full_name: String,
    val id: Int,
    val name: String,
    val url: String,
    val variant: String,
    )

data class FlightData(
    val agency_launch_attempt_count: Int,
    val agency_launch_attempt_count_year: Int,
    val failreason: String,
    val hashtag: Any,
    val holdreason: String,
    val id: String,
    val image: String,
    val infographic: String,
    val last_updated: String,
    val launch_service_provider: LaunchServiceProvider,
    val location_launch_attempt_count: Int,
    val location_launch_attempt_count_year: Int,
    val mission: Mission,
    val name: String,
    val net: String,
    val orbital_launch_attempt_count: Int,
    val orbital_launch_attempt_count_year: Int,
    val pad: Pad,
    val pad_launch_attempt_count: Int,
    val pad_launch_attempt_count_year: Int,
    val probability: Int,
    val program: List<Program>,
    val rocket: Rocket,
    val slug: String,
    val status: Status,
    val url: String,
    val webcast_live: Boolean,
    val window_end: String,
    val window_start: String
)

data class Landing(
    val destination: String,
    val id: Int,
    val landing: Any,
    val launch: Launch,
    val mission_end: String,
    val spacecraft: Spacecraft,
    val url: String
)

data class Launch(
    val agency_launch_attempt_count: Int,
    val agency_launch_attempt_count_year: Int,
    val failreason: String,
    val hashtag: Any,
    val holdreason: String,
    val id: String,
    val image: String,
    val infographic: String,
    val last_updated: String,
    val launch_service_provider: LaunchServiceProvider,
    val location_launch_attempt_count: Int,
    val location_launch_attempt_count_year: Int,
    val mission: MissionX,
    val name: String,
    val net: String,
    val orbital_launch_attempt_count: Int,
    val orbital_launch_attempt_count_year: Int,
    val pad: PadX,
    val pad_launch_attempt_count: Int,
    val pad_launch_attempt_count_year: Int,
    val probability: Int,
    val program: List<Program>,
    val rocket: RocketX,
    val slug: String,
    val status: Status,
    val url: String,
    val webcast_live: Boolean,
    val window_end: String,
    val window_start: String
)
data class LaunchServiceProvider(
    val id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class Location(
    val country_code: String,
    val id: Int,
    val map_image: String,
    val name: String,
    val timezone_name: String,
    val total_landing_count: Int,
    val total_launch_count: Int,
    val url: String
)

data class Mission(
    val description: String,
    val id: Int,
    val launch_designator: Any,
    val name: String,
    val orbit: Orbit,
    val type: String
)

data class MissionX(
    val description: String,
    val id: Int,
    val launch_designator: Any,
    val name: String,
    val orbit: Orbit,
    val type: String
)

data class Orbit(
    val abbrev: String,
    val id: Int,
    val name: String
)

data class Pad(
    val agency_id: Any,
    val id: Int,
    val info_url: Any,
    val latitude: String,
    val location: Location,
    val longitude: String,
    val map_image: String,
    val map_url: String,
    val name: String,
    val orbital_launch_attempt_count: Int,
    val total_launch_count: Int,
    val url: String,
    val wiki_url: String
)

data class PadX(
    val agency_id: Any,
    val id: Int,
    val info_url: Any,
    val latitude: String,
    val location: Location,
    val longitude: String,
    val map_image: String,
    val map_url: String,
    val name: String,
    val orbital_launch_attempt_count: Int,
    val total_launch_count: Int,
    val url: String,
    val wiki_url: String
)

data class Program(
    val agencies: List<Agency>,
    val description: String,
    val end_date: Any,
    val id: Int,
    val image_url: String,
    val info_url: String,
    val mission_patches: List<Any>,
    val name: String,
    val start_date: String,
    val url: String,
    val wiki_url: String
)

data class Rocket(
    val configuration: Configuration,
    val id: Int
)

data class RocketX(
    val configuration: Configuration,
    val id: Int
)

data class Spacecraft(
    val description: String,
    val id: Int,
    val name: String,
    val serial_number: String,
    val spacecraft_config: SpacecraftConfig,
    val status: StatusXX,
    val url: String
)

data class SpacecraftConfig(
    val agency: Agency,
    val id: Int,
    val image_url: String,
    val in_use: Boolean,
    val name: String,
    val type: SpacecraftConfigType,
    val url: String
)
data class SpacecraftConfigType(
    val id: Int,
    val name: String
)
data class Status(
    val abbrev: String,
    val description: String,
    val id: Int,
    val name: String
)

data class StatusXX(
    val id: Int,
    val name: String
)

data class TypeX(
    val id: Int,
    val name: String
)