@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.flights.flux

import com.kinley.features.ext.Ignore
import com.kinley.features.ext.exhaustive
import com.kinley.features.flights.flux.FlightActions.*
import com.kinley.features.flights.flux.middleware.FlightActionProcessor
import com.kinley.features.flux.Store
import com.kinley.features.flux.setState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FlightStore(
    private val actionProcessors: List<FlightActionProcessor>
) : Store<FlightState, FlightActions>,
    CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    override var state: MutableStateFlow<FlightState> = MutableStateFlow(FlightState.initState())

    override fun dispatchActions(action: FlightActions) = processIntent(action)

    override fun stateStream(): StateFlow<FlightState> = state

    private fun processIntent(action: FlightActions) {
        launch {
            val newAction = getProcessedAction(action)
            consumeAction(newAction)

        }
    }

    // Handle it in a better way, may be with sequence
    private suspend fun getProcessedAction(action: FlightActions): FlightActions {
        var processedAction = action
        actionProcessors.forEach {
            processedAction = it.processAction(this@FlightStore, processedAction)
        }
        return processedAction
    }

    private fun consumeAction(action: FlightActions) {
        when (action) {
            is FlightsFetched -> setState { copy(flights = action.flights, loading = false) }
            is FlightSelected -> {
                val selectedFlight = state.value.flights.first { it.identifier == action.selectedFlightId }
                setState { copy(selectedFlight = selectedFlight) }
            }
            is FetchFlights -> Ignore()
            is RemoveSelectedFlight -> setState { copy(selectedFlight = null) }
            is Loading -> Ignore()
        }.exhaustive
    }

}
