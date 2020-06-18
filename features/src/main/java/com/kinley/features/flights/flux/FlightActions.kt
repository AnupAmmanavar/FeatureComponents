package com.kinley.features.flights.flux

import com.kinley.features.featurecomponent.flux.Action
import com.kinley.features.flights.domain.Flight

sealed class FlightActions: Action

data class FlightsFetched(val flights: List<Flight>): FlightActions()
data class DateChanged(val date: String): FlightActions()
