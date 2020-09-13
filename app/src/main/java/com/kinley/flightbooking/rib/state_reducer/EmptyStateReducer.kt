@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.flightbooking.rib.state_reducer

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Empty

/**
 * This is a useful for a StateLess RIB
 */
class EmptyStateReducer : StateReducer<Empty> {

    override val state: Empty = Empty()
    override val stateStream: StateFlow<Empty> = MutableStateFlow(state)

    override suspend fun updateState(modifier: Empty.() -> Empty) = state

}
