@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.flights

import com.kinley.features.featurecomponent.FeatureComponent
import com.kinley.features.flights.domain.Flight
import com.kinley.features.flights.flux.FlightActions.FetchFlights
import com.kinley.features.flights.flux.FlightActions.FlightSelected
import com.kinley.features.flights.flux.FlightReducer
import com.kinley.features.flights.flux.FlightState
import com.kinley.features.flights.flux.FlightStore
import com.kinley.features.flights.presentation.FlightUiModel
import com.kinley.features.flights.presentation.FlightView
import com.kinley.features.flights.presentation.FlightsUiState
import com.kinley.features.flights.setup.FlightEventDispatcher
import com.kinley.features.flights.setup.FlightEventReceiver
import com.kinley.features.flights.setup.FlightUiDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import java.util.*


@ExperimentalCoroutinesApi
class FlightFeatureComponent(
    override val eventDispatcher: FlightEventDispatcher
) : FeatureComponent<FlightView, FlightEventDispatcher>,
    FlightEventReceiver,
    FlightUiDelegate, CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    private val store: FlightStore = FlightStore(FlightReducer())

    private val uiState = MutableStateFlow(FlightsUiState())

    init {
        store.stateStream()
            .distinctUntilChanged { old, new -> old == new }
            .onEach { render(it) }
            .launchIn(this)


        /**
         * Some change in events that affected by state change need to be propogated to the Outside world
         */
        store.stateStream()
            .distinctUntilChanged { old, new -> old.selectedFlight == new.selectedFlight }
            .map { it.selectedFlight }
            .filterNotNull()
            .onEach {
                eventDispatcher.onFlightSelection(it)
            }
            .launchIn(this)

    }

    private fun render(state: FlightState) {

        val flightsUiModel = state.flights.map { flight ->
            FlightUiModel(
                flightName = flight.flightName,
                airline = flight.airlines,
                flightCost = flight.cost
            )
        }
        uiState.value =
            FlightsUiState(
                flightsUiModel
            )

    }

    override fun dateChange(date: Date) {
        fetchFlights(date)
    }

    override fun flightClick(flight: Flight) {
        store.dispatchActions(FlightSelected(flight))
    }

    private fun fetchFlights(date: Date) {
        store.dispatchActions(FetchFlights(date))
    }


    override fun render(view: FlightView) {
        view.create(uiState, this@FlightFeatureComponent)
    }

}


