package com.kinley.features.flights.flux

import com.kinley.features.flights.domain.Flight

/**
 * TODO Loading and Error should not be handled here
 */
data class FlightState(
    val flights: List<Flight>,
    val date: String?,
    val selectedFlight: Flight?,
    val loading: Boolean
)
