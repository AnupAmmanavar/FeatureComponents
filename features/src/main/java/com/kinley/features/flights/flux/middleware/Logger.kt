package com.kinley.features.flights.flux.middleware

import android.util.Log
import com.kinley.features.flights.flux.FlightActions
import com.kinley.features.flights.flux.FlightStore

class Logger :
    FlightActionProcessor {

    override suspend fun processAction(
        store: FlightStore,
        action: FlightActions
    ): FlightActions {
        Log.d("Logger", "$action")
        return action
    }

}
