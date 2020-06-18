@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.flights.flux

import com.kinley.features.featurecomponent.flux.Reducer
import com.kinley.features.featurecomponent.flux.withState
import com.kinley.features.flights.domain.Flight
import kotlinx.coroutines.flow.MutableStateFlow

class FlightReducer : Reducer<FlightState> {

    override var state: MutableStateFlow<FlightState> =
        MutableStateFlow(FlightState(arrayListOf(), null, null))

    fun updateFlights(flights: List<Flight>) = withState {
        it.copy(flights = flights)
    }

    fun updateSelectedFlight(selectedFlight: Flight) = withState {
        it.copy(selectedFlight = selectedFlight)
    }
}

