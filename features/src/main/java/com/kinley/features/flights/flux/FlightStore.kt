@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.flights.flux

import com.kinley.features.ext.exhaustive
import com.kinley.features.flights.FlightRepository
import com.kinley.features.flights.flux.FlightActions.*
import com.kinley.features.flux.Store
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*


class FlightStore(
    private val reducer: FlightReducer
) : Store<FlightState, FlightActions>,
    CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    private val repository: FlightRepository =
        FlightRepository()

    override fun dispatchActions(action: FlightActions) = processIntent(action)

    private fun processIntent(action: FlightActions) {
        launch {
            when (action) {
                is FlightsFetched -> reducer.updateFlights(action.flights)
                is FlightSelected -> {
                    val selectedFlight = reducer.state.value.flights.first { it.flightName == action.selectedFlightId }
                    reducer.updateSelectedFlight(selectedFlight)
                }
                is FetchFlights -> fetchFlights()
            }.exhaustive
        }
    }

    //TODO Can be kept separate
    private suspend fun fetchFlights() {
        reducer.setLoading()
        val flights = repository.fetchFlights(Date())
        dispatchActions(FlightsFetched(flights))
    }

    override fun stateStream(): StateFlow<FlightState> = reducer.state

}
