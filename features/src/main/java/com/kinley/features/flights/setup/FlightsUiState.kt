package com.kinley.features.flights.setup

/**
 * It composes of multiple UiModels.
 */
data class FlightsUiState(
    val flightsUiModel: List<FlightUiModel> = arrayListOf()
)

/**
 * A piece of UI is associated with the UiModel which determines its state
 */
data class FlightUiModel(
    val flightName: String,
    val airline: String,
    val flightCost: Double
)

