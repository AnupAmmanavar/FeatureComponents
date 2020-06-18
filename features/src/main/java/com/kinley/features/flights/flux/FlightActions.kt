package com.kinley.features.flights.flux

import com.kinley.features.featurecomponent.flux.Action
import com.kinley.features.flights.domain.Flight
import java.util.*

sealed class FlightActions: Action

data class FlightsFetched(val flights: List<Flight>): FlightActions()
data class FetchFlights(val date: Date): FlightActions()
data class FlightSelected(val selectedFlight: Flight): FlightActions()
