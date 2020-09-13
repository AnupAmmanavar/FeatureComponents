package com.kinley.flightbooking.rib.usage.sample1

import com.kinley.flightbooking.rib.interactor.ContainerRibState
import com.kinley.flightbooking.rib.interactor.ContainerStateReducer
import com.kinley.flightbooking.rib.presentation.VMMapper
import com.kinley.flightbooking.rib.presentation.view_updater.StateChangeViewUpdater
import com.kinley.flightbooking.rib.presentation.view_updater.ViewUpdater
import com.kinley.flightbooking.rib.state_reducer.state_provider.StateProvider
import com.kinley.flightbooking.rib.state_reducer.state_provider.StateProviderFromStateReducer
import com.kinley.flightbooking.rib.usage.sample1.state.EarningsState
import com.kinley.flightbooking.rib.usage.sample1.state.EarningsStateReducer
import com.kinley.flightbooking.rib.usage.sample1.view.EarningsPresenter
import com.kinley.flightbooking.rib.usage.sample1.view.EarningsVM
import com.kinley.flightbooking.rib.usage.sample1.view.EarningsVMMapper
import com.kinley.flightbooking.rib.usage.sample2.EarningsInteractorV2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class EarningsBuilder(
    private val presenter: EarningsPresenter,
    private val coroutineContext: CoroutineContext
) {

    fun build(): EarningsInteractor {

        val stateReducer: EarningsStateReducer = provideStateReducer()
        val stateProvider = provideStateProvider(stateReducer)
        val viewUpdater: ViewUpdater<EarningsState, EarningsVM> = provideViewUpdater(stateProvider, stateReducer.stateStream)

        return EarningsInteractor(
            stateReducer = stateReducer,
            coroutineContext = coroutineContext,
            viewUpdater = viewUpdater
        )
    }

    private fun provideStateReducer() =
        EarningsStateReducer(EarningsState(0.0), stateDispatcher = Dispatchers.Default)

    private fun provideStateProvider(stateReducer: EarningsStateReducer): StateProvider<EarningsState> =
        StateProviderFromStateReducer(stateReducer)


    private fun provideViewUpdater(stateProvider: StateProvider<EarningsState>, stateStream: Flow<EarningsState>) : ViewUpdater<EarningsState, EarningsVM> =
        StateChangeViewUpdater(
            presenter = presenter,
            vmMapper = EarningsVMMapper(),
            coroutineContext = coroutineContext,
            stateProvider = stateProvider,
            stateStream = stateStream
        )
}
