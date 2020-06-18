package com.kinley.features.flights

import com.kinley.features.featurecomponent.EventDispatcher

/**
 * Dispatches the events from the FlightFeatureComponent. This is like a channel through which we sent events to the outside world
 */
interface FlightEventDispatcher :
    EventDispatcher {
    fun onFlightSelection(flight: String)
}
