package com.kinley.features.flights.flux

import com.kinley.features.flights.FlightRepository
import com.kinley.features.flux.FlightActionProcessor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*

class FlightApi(
    private val flightRepository: FlightRepository
) : FlightActionProcessor, CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    override suspend fun processAction(store: FlightStore, action: FlightActions) =
        coroutineScope {
            when (action) {
                is FlightActions.FetchFlights -> {
                    launch {
                        val flights = flightRepository.fetchFlights(Date())
                        store.dispatchActions(FlightActions.FlightsFetched(flights))
                    }
                    FlightActions.Loading
                }
                else -> action
            }
        }

}
