@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.flights.flux

import com.kinley.features.featurecomponent.flux.Store
import kotlinx.coroutines.flow.MutableStateFlow


class FeatureStore(
    val reducer: FlightReducer
) : Store<FlightState, FlightActions> {

    override val state: MutableStateFlow<FlightState> = reducer.state

    override fun dispatchActions(action: FlightActions) = processIntent(action)

    private fun processIntent(action: FlightActions) {
        when (action) {
            is FlightsFetched -> TODO()
            is DateChanged -> TODO()
        }.exhaustive
    }

}


val <T> T.exhaustive: T
    get() = this
