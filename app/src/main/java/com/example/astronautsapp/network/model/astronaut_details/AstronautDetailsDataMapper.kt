package com.example.astronautsapp.network.model.astronaut_details

import com.example.astronautsapp.domain.DomainMapper
import com.example.astronautsapp.domain.model.AstronautDetails
import com.example.astronautsapp.domain.model.Flight

class AstronautDetailsDataMapper: DomainMapper<AstronautDetailsApiModel, AstronautDetails> {
    override fun mapToDomainModel(data: AstronautDetailsApiModel): AstronautDetails {

        return AstronautDetails(
            name = data.name,
            profileImg = data.profile_image,
            flights = mapFlights(data.flights)
        )
    }

    private fun mapFlight(flight: FlightData): Flight {
        return Flight( name = flight.name )
    }

    private fun mapFlights(flights: List<FlightData>): List<Flight> {
        return  flights.map { mapFlight(it)}
    }
}