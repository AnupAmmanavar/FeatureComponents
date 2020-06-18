@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.flights.flux

import com.kinley.features.featurecomponent.flux.Reducer
import com.kinley.features.featurecomponent.flux.setState
import com.kinley.features.flights.domain.Flight
import kotlinx.coroutines.flow.MutableStateFlow

class FlightReducer : Reducer<FlightState> {

    override var state: MutableStateFlow<FlightState> =
        MutableStateFlow(FlightState(arrayListOf(), null, null, false))

    fun updateFlights(flights: List<Flight>) = setState {
        copy(flights = flights, loading = false)
    }

    fun updateSelectedFlight(selectedFlight: Flight) = setState {
        copy(selectedFlight = selectedFlight)
    }

    fun setLoading() = setState {
        copy(loading = true)
    }
}

