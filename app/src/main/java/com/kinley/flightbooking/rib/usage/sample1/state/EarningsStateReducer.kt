@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.flightbooking.rib.usage.sample1.state

import com.kinley.flightbooking.rib.state_reducer.AbstractStateReducer
import kotlinx.coroutines.CoroutineDispatcher

class EarningsStateReducer(
    initialState: EarningsState,
    stateDispatcher: CoroutineDispatcher
) : AbstractStateReducer<EarningsState>(initialState, stateDispatcher) {

}
