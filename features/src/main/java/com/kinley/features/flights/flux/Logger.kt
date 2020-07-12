package com.kinley.features.flights.flux

import android.util.Log
import com.kinley.features.flux.FlightActionProcessor

class Logger : FlightActionProcessor {

    override suspend fun processAction(
        store: FlightStore,
        action: FlightActions
    ): FlightActions {
        Log.d("Logger", "$action")
        return action
    }

}
