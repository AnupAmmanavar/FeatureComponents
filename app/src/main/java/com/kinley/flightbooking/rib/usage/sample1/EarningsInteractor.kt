package com.kinley.flightbooking.rib.usage.sample1

import com.kinley.flightbooking.rib.interactor.StatefulInteractor
import com.kinley.flightbooking.rib.presentation.view_updater.ViewUpdater
import com.kinley.flightbooking.rib.usage.sample1.state.EarningsState
import com.kinley.flightbooking.rib.usage.sample1.state.EarningsStateReducer
import com.kinley.flightbooking.rib.usage.sample1.view.EarningsVM
import kotlin.coroutines.CoroutineContext

class EarningsInteractor(
    stateReducer: EarningsStateReducer,
    coroutineContext: CoroutineContext,
    private val viewUpdater: ViewUpdater<EarningsState, EarningsVM>
) : StatefulInteractor<EarningsState>(stateReducer, coroutineContext) {

    override fun didBecomeActive() {
        super.didBecomeActive()

        viewUpdater.observe()

    }
}

