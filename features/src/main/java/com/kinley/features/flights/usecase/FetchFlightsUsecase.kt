package com.kinley.features.flights.usecase

import com.kinley.features.flights.FlightRepository
import com.kinley.features.flights.domain.Flight
import java.util.*


class FetchFlightsUsecase(
    private val repository: FlightRepository
) {
    suspend fun invoke(onSuccess: (List<Flight>) -> Unit) {
        val flights = repository.fetchFlights(Date())
        onSuccess(flights)
    }

}
