package com.kinley.features.flights

import com.kinley.features.featurecomponent.EventReceiver
import java.util.*

/**
 * This is a gateway through which it receives different events from the outside world.
 */
interface FlightEventReceiver :
    EventReceiver {
    fun dateChange(date: Date)
}
