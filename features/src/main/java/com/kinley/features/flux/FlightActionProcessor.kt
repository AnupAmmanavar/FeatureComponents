package com.kinley.features.flux

import com.kinley.features.flights.flux.FlightActions
import com.kinley.features.flights.flux.FlightStore

interface FlightActionProcessor {
    suspend fun processAction(store: FlightStore, action: FlightActions): FlightActions
}


interface Next {
    fun next(store: FlightStore, action: FlightActions): Action
}


