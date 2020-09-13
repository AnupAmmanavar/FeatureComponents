@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.flightbooking.rib.state_reducer

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

/**
 * This is a base [StateReducer] that implements the [StateReducer.updateState] function.
 * StateReducer of all RIBS should extend this
 */
abstract class AbstractStateReducer<S>(
    initialState: S,
    private val stateDispatcher: CoroutineDispatcher
): StateReducer<S> {

    private val mutableStateFlow = MutableStateFlow(initialState)

    override val state: S = mutableStateFlow.value
    override val stateStream: StateFlow<S> = mutableStateFlow

    override suspend fun updateState(modifier: S.() -> S): S =
        withContext(stateDispatcher) {
            val newState = this@AbstractStateReducer.state.modifier()
            mutableStateFlow.value = newState
            newState
        }
}
