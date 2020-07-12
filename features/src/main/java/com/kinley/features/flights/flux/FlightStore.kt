@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.flights.flux

import android.util.Log
import com.kinley.features.ext.Ignore
import com.kinley.features.ext.exhaustive
import com.kinley.features.flights.FlightRepository
import com.kinley.features.flights.flux.FlightActions.*
import com.kinley.features.flux.FlightActionProcessor
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

    private val actionProcessors = listOf(Logger(), FlightApi(FlightRepository()))

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

    inner class Logger : FlightActionProcessor {

        override suspend fun processAction(
            store: FlightStore,
            action: FlightActions
        ): FlightActions {
            Log.d("Logger", "$action")
            return action
        }

    }

    inner class FlightApi(
        private val flightRepository: FlightRepository
    ) : FlightActionProcessor {

        override suspend fun processAction(store: FlightStore, action: FlightActions) =
            when (action) {
                is FetchFlights -> {
                    launch {
                        val flights = flightRepository.fetchFlights(Date())
                        store.dispatchActions(FlightsFetched(flights))
                    }
                    Loading
                }
                else -> action
            }

    }

}
