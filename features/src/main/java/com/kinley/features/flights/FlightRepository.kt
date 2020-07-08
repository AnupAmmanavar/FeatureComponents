package com.kinley.features.flights

import com.kinley.features.flights.domain.Flight
import com.kinley.features.hotels.DataFactory
import java.util.*

class FlightRepository {
    suspend fun fetchFlights(date: Date): List<Flight> {
        return listOf("Air India", "Qatar", "Air Asia", "Indigo", "SpiceJet", "Vistara", "Go Air", "Etihad Airways")
            .map {
                Flight(UUID.randomUUID().toString(), it, it, DataFactory.provideInt())
            }
    }
}
