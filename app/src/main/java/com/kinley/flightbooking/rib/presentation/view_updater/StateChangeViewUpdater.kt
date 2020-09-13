package com.kinley.flightbooking.rib.presentation.view_updater

import com.kinley.flightbooking.rib.presentation.Presenter
import com.kinley.flightbooking.rib.presentation.VMMapper
import com.kinley.flightbooking.rib.state_reducer.state_provider.StateProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Observes only the changes in State
 */
class StateChangeViewUpdater<S, VM>(
    presenter: Presenter<VM>,
    vmMapper: VMMapper<S, VM>,
    stateProvider: StateProvider<S>,
    private val stateStream: Flow<S>,
    override val coroutineContext: CoroutineContext
) : ViewUpdater<S, VM>(presenter, stateProvider, vmMapper), CoroutineScope {

    override fun observe() {
        launch {
            stateStream
                .collect { trigger() }
        }
    }

}

