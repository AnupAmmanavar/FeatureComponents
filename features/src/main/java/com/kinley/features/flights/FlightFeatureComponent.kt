package com.kinley.features.flights

import com.kinley.features.featurecomponent.FeatureComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalCoroutinesApi
class FlightFeatureComponent(
    override val eventDispatcher: FlightEventDispatcher
) : FeatureComponent<FlightView, FlightEventDispatcher>, FlightEventReceiver,
    FlightUiDelegate, CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    private val uiState = MutableStateFlow(FlightsUiState())

    override fun dateChange(date: String) {
        fetchFlights(date)
    }

    override fun flightRange(minAmount: Double) {
        TODO("Not yet implemented")
    }

    override fun flightClick(flight: String) {
        eventDispatcher.onFlightSelection(flight)
    }

    private fun fetchFlights(date: String) {
        // Make api call to fetch flights for the given date
    }


    override fun render(view: FlightView) {
        view.create(uiState, this@FlightFeatureComponent)
    }

}


