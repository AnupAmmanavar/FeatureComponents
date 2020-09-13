@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.flightbooking.rib.state_reducer

import kotlinx.coroutines.flow.StateFlow

interface StateReducer<S> {
    val state: S
    val stateStream: StateFlow<S>

    /**
     * This updates the [state] of the RIB
     */
    suspend fun updateState(modifier: S.() -> S) : S
}

