package com.kinley.features.flights.domain

data class Flight(
    val identifier: String,
    val flightName: String,
    val airlines: String,
    val cost: Int
)
