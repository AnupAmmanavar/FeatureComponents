package com.kinley.features.flights

data class FlightsUiState(
    val flightsUiModel: List<FlightUiModel> = arrayListOf()
)

data class FlightUiModel(
    val flightName: String,
    val airline: String,
    val flightCost: Double
)

