package com.kinley.features.flights.setup

import com.kinley.features.featurecomponent.EventDispatcher
import com.kinley.features.flights.domain.Flight

/**
 * Dispatches the events from the FlightFeatureComponent. This is like a channel through which we sent events to the outside world
 * There are two types of event
 * 1. Change in FeatureState needs to be propagated
 * 2. Other events like Clicks that are controlled by the parent and not handled by the current FeatureComponent
 */
interface FlightEventDispatcher : EventDispatcher {
    fun onFlightSelection(flight: Flight)
}
