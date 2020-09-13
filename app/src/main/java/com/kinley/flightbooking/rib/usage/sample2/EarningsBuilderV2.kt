package com.kinley.flightbooking.rib.usage.sample2

import com.kinley.flightbooking.rib.interactor.ContainerRibState
import com.kinley.flightbooking.rib.interactor.ContainerStateReducer
import com.kinley.flightbooking.rib.presentation.VMMapper
import com.kinley.flightbooking.rib.presentation.view_updater.StateChangeViewUpdater
import com.kinley.flightbooking.rib.presentation.view_updater.ViewUpdater
import com.kinley.flightbooking.rib.state_reducer.state_provider.StateProvider
import com.kinley.flightbooking.rib.state_reducer.state_provider.StateProviderFromStateReducer
import com.kinley.flightbooking.rib.usage.sample1.state.EarningsState
import com.kinley.flightbooking.rib.usage.sample1.view.EarningsPresenter
import com.kinley.flightbooking.rib.usage.sample1.view.EarningsVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class EarningsBuilderV2(
    private val presenter: EarningsPresenter,
    private val coroutineContext: CoroutineContext
) {

    fun build(): EarningsInteractorV2 {

        val stateReducer: ContainerStateReducer<EarningsState> = provideContainerLoadingState()

        val stateProvider = StateProviderFromStateReducer(stateReducer)
        val viewUpdater: ViewUpdater<ContainerRibState<EarningsState>, EarningsVM> =
            provideContainerViewUpdater(stateProvider, stateReducer.stateStream)

        return EarningsInteractorV2(
            stateReducer = stateReducer,
            coroutineContext = coroutineContext,
            viewUpdater = viewUpdater
        )
    }

    private fun <S> provideContainerLoadingState(loadingMsg: String = "Loading..."): ContainerStateReducer<S> =
        ContainerStateReducer(ContainerRibState.Loading(loadingMsg), Dispatchers.Default)

    private fun provideContainerViewUpdater(
        stateProvider: StateProvider<ContainerRibState<EarningsState>>,
        stateStream: Flow<ContainerRibState<EarningsState>>
    ): ViewUpdater<ContainerRibState<EarningsState>, EarningsVM> =
        StateChangeViewUpdater(
            presenter = presenter,
            vmMapper = object : VMMapper<ContainerRibState<EarningsState>, EarningsVM> {
                override fun map(state: ContainerRibState<EarningsState>): EarningsVM {
                    TODO("Not yet implemented")
                }

            },
            coroutineContext = coroutineContext,
            stateProvider = stateProvider,
            stateStream = stateStream
        )
}
