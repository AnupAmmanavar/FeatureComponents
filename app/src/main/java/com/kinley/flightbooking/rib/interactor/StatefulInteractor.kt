package com.kinley.flightbooking.rib.interactor

import com.kinley.flightbooking.rib.state_reducer.StateReducer
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

open class StatefulInteractor<S>(
    override val stateReducer: StateReducer<S>,
    override val coroutineContext: CoroutineContext
): Interactor<S> {

    override fun didBecomeActive() {}

    override fun willResignActive() {
        cancel()
    }
}
