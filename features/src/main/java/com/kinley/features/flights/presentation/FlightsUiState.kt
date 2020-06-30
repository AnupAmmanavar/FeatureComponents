package com.kinley.features.flights.presentation

/**
 * It composes of multiple UiModels.
 */
data class FlightsUiState(
    val flightsUiModel: List<FlightUiModel> = arrayListOf()
)
