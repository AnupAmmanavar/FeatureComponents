package com.kinley.features.flights

import com.kinley.features.featurecomponent.EventReceiver

/**
 * This is a gateway through which it receives different events from the outside world.
 */
interface FlightEventReceiver :
    EventReceiver {
    fun dateChange(date: String)

    fun flightRange(minAmount: Double)
}
