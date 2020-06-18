package com.kinley.features.flights

import com.kinley.features.flights.domain.Flight

/**
 * Handles the Ui Events from the FlightsView
 */
interface FlightUiDelegate {
    fun flightClick(flight: Flight)
}
