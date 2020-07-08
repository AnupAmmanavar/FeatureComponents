@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.flights

import com.kinley.features.featurecomponent.FeatureComponent
import com.kinley.features.flights.flux.FlightActions
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
import kotlinx.coroutines.flow.*
import java.util.*


class FlightFeatureComponent(
    override val eventDispatcher: FlightEventDispatcher
) : FeatureComponent<FlightView, FlightEventDispatcher>,
    FlightEventReceiver,
    FlightUiDelegate, CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    private val store: FlightStore = FlightStore(FlightReducer())

    private val uiState = MutableStateFlow(FlightsUiState.initState())

    init {
        store.stateStream()
            .distinctUntilChanged { old, new -> old == new }
            .onEach { recomposeView(it) }
            .launchIn(this)


        /**
         * Some change in events that affected by state change need to be propagated to the Outside world
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

    override fun render(view: FlightView) {
        view.create(uiState, this@FlightFeatureComponent)
        store.dispatchActions(FetchFlights(Date()))
    }

    override fun dateChange(date: Date) {
        fetchFlights(date)
    }

    override fun onRemoveSelection() {
        store.dispatchActions(FlightActions.RemoveSelectedFlight)
    }

    override fun flightClick(flightId: String) {
        store.dispatchActions(FlightSelected(flightId))
    }

    private fun fetchFlights(date: Date) {
        store.dispatchActions(FetchFlights(date))
    }

    private fun recomposeView(state: FlightState) {

        val flightsUiModel = state.flights.map { flight ->
            FlightUiModel(
                identifier = flight.identifier,
                flightName = flight.flightName,
                airline = flight.airlines,
                flightCost = flight.cost
            )
        }
        uiState.value = FlightsUiState(flightsUiModel, state.selectedFlight?.identifier)

    }

}


