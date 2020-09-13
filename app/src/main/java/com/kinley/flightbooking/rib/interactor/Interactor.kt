@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.flightbooking.rib.interactor

import com.kinley.flightbooking.rib.state_reducer.StateReducer
import kotlinx.coroutines.CoroutineScope

/**
 * Interactor is where the business-logic is written.
 * Business-logic deals with State [S]. Hence we have a reference of [StateReducer] in its dependency.
 */
interface Interactor<S> : CoroutineScope {
    val stateReducer: StateReducer<S>
    fun didBecomeActive()
    fun willResignActive()
}
