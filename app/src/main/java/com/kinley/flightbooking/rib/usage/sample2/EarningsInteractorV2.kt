package com.kinley.flightbooking.rib.usage.sample2

import com.kinley.flightbooking.rib.interactor.ContainerRibInteractor
import com.kinley.flightbooking.rib.interactor.ContainerRibState
import com.kinley.flightbooking.rib.interactor.ContainerStateReducer
import com.kinley.flightbooking.rib.presentation.view_updater.ViewUpdater
import com.kinley.flightbooking.rib.usage.sample1.state.EarningsState
import com.kinley.flightbooking.rib.usage.sample1.view.EarningsVM
import kotlin.coroutines.CoroutineContext

class EarningsInteractorV2(
    stateReducer: ContainerStateReducer<EarningsState>,
    coroutineContext: CoroutineContext,
    private val viewUpdater: ViewUpdater<ContainerRibState<EarningsState>, EarningsVM>
): ContainerRibInteractor<EarningsState>(stateReducer, coroutineContext) {

}
