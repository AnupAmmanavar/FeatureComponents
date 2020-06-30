package com.kinley.features.flights.presentation

/**
 * A piece of UI is associated with the UiModel which determines its state
 */
data class FlightUiModel(
    val flightName: String,
    val airline: String,
    val flightCost: Double
)
