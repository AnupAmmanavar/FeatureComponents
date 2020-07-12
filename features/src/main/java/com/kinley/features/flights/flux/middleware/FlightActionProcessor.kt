package com.kinley.features.flights.flux.middleware

import com.kinley.features.flights.flux.FlightActions
import com.kinley.features.flights.flux.FlightStore

interface FlightActionProcessor {
    suspend fun processAction(store: FlightStore, action: FlightActions): FlightActions
}
