package com.kinley.flightbooking.rib.state_reducer.state_provider

import com.kinley.flightbooking.rib.state_reducer.StateReducer

/**
 * This is an implementation of [StateProvider],
 * It fetches the state from [StateReducer]
 */
class StateProviderFromStateReducer<S>(
    private val stateReducer: StateReducer<S>
) : StateProvider<S> {
    override fun getState() = stateReducer.state
}
