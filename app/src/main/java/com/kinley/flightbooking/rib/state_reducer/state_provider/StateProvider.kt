package com.kinley.flightbooking.rib.state_reducer.state_provider

/**
 * This interface provides the state
 */
interface StateProvider<S> {
    fun getState(): S
}
