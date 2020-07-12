@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.flights.flux

import com.kinley.features.ext.Ignore
import com.kinley.features.ext.exhaustive
import com.kinley.features.flights.flux.FlightActions.*
import com.kinley.features.flights.flux.middleware.FlightActionProcessor
import com.kinley.features.flux.Store
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FlightStore(
    private val reducer: FlightReducer,
    private val actionProcessors: List<FlightActionProcessor>
) : Store<FlightState, FlightActions>,
    CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    override fun dispatchActions(action: FlightActions) = processIntent(action)

    private fun processIntent(action: FlightActions) {
        launch {
            var processedAction = action
            actionProcessors.forEach {
                processedAction = it.processAction(this@FlightStore, processedAction)
            }

            when (val newAction = processedAction) {
                is FlightsFetched -> reducer.updateFlights(newAction.flights)
                is FlightSelected -> {
                    val selectedFlight =
                        reducer.state.value.flights.first { it.identifier == newAction.selectedFlightId }
                    reducer.updateSelectedFlight(selectedFlight)
                }
                is FetchFlights -> Ignore()
                is RemoveSelectedFlight -> reducer.removeFlightSelection()
                is Loading -> Ignore()
            }.exhaustive
        }
    }

    override fun stateStream(): StateFlow<FlightState> = reducer.state

}
