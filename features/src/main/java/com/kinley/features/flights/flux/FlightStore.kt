@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.flights.flux

import com.kinley.features.featurecomponent.flux.Store
import kotlinx.coroutines.flow.StateFlow


class FeatureStore(
    private val reducer: FlightReducer
) : Store<FlightState, FlightActions> {

    override fun stateStream(): StateFlow<FlightState> = reducer.state

    override fun dispatchActions(action: FlightActions) = processIntent(action)

    private fun processIntent(action: FlightActions) {
        when (action) {
            is FlightsFetched -> reducer.updateFlights(action.flights)
            is DateChanged -> TODO()
            is FlightSelected -> reducer.updateSelectedFlight(action.selectedFlight)
        }.exhaustive
    }

}


val <T> T.exhaustive: T
    get() = this
