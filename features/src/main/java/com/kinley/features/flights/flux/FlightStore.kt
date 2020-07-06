@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.flights.flux

import com.kinley.features.featurecomponent.flux.Store
import com.kinley.features.flights.flux.FlightActions.*
import kotlinx.coroutines.flow.StateFlow


class FlightStore(
    private val reducer: FlightReducer
) : Store<FlightState, FlightActions> {

    override fun dispatchActions(action: FlightActions) = processIntent(action)

    private fun processIntent(action: FlightActions) {
        when (action) {
            is FlightsFetched -> reducer.updateFlights(action.flights)
            is FlightSelected -> reducer.updateSelectedFlight(action.selectedFlight)
            is FetchFlights -> reducer.setLoading()
        }.exhaustive
    }

    override fun stateStream(): StateFlow<FlightState> = reducer.state

}


val <T> T.exhaustive: T
    get() = this
