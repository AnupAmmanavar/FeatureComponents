package com.kinley.features.flights.presentation

/**
 * A piece of UI is associated with the UiModel which determines its state
 */
data class FlightUiModel(
    val identifier: String,
    val flightName: String,
    val airline: String,
    val flightCost: Int
)
