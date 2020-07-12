package com.kinley.features.flights.flux

import com.kinley.features.flights.domain.Flight
import com.kinley.features.flux.Action
import java.util.*

sealed class FlightActions : Action {

    // API call fetches the new list of Flights
    data class FlightsFetched(val flights: List<Flight>) : FlightActions()

    // Request to fetch the flights for the specified date
    data class FetchFlights(val date: Date) : FlightActions()

    // Updates the selected flight
    data class FlightSelected(val selectedFlightId: String) : FlightActions()

    object RemoveSelectedFlight : FlightActions()

    object Loading : FlightActions()
}
