package com.kinley.features.flights.flux.middleware

import com.kinley.features.flights.flux.FlightActions
import com.kinley.features.flights.flux.FlightStore
import com.kinley.features.flux.Action

interface FlightActionProcessor {
    suspend fun processAction(store: FlightStore, action: FlightActions): FlightActions
}


interface Next {
    fun next(store: FlightStore, action: FlightActions): Action
}


