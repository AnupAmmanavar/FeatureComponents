package com.kinley.features.flights.flux

import com.kinley.features.flights.domain.Flight

data class FlightState(
    val flights: List<Flight>,
    val date: String?,
    val selectedFlight: Flight?
)
