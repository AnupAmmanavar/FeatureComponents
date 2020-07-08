package com.kinley.features.flights.setup

import com.kinley.features.featurecomponent.EventReceiver
import java.util.*

/**
 * This is a gateway through which it receives different events from the outside world.
 */
interface FlightEventReceiver : EventReceiver {
    // Change in date will let the FlightComponent to update itself.
    // Makes an API call to fetch the flights for the specified date
    fun dateChange(date: Date)

    fun onRemoveSelection()
}
