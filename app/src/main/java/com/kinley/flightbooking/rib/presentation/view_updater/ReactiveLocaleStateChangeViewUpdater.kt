package com.kinley.flightbooking.rib.presentation.view_updater

import com.kinley.flightbooking.rib.presentation.Presenter
import com.kinley.flightbooking.rib.presentation.VMMapper
import com.kinley.flightbooking.rib.state_reducer.state_provider.StateProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlin.coroutines.CoroutineContext

class Locale

/**
 * Observes the changes in State and Locale.
 * This is similar to our VMMapper2
 */
class ReactiveLocaleStateChangeViewUpdater<S, VM>(
    presenter: Presenter<VM>,
    stateProvider: StateProvider<S>,
    vmMapper: VMMapper<S, VM>,
    private val localeStream: Flow<Locale>,
    private val stateStream: Flow<S>,
    override val coroutineContext: CoroutineContext
) : ViewUpdater<S, VM>(presenter, stateProvider, vmMapper), CoroutineScope {

    override fun observe() {
        combine(stateStream.distinctUntilChanged(), localeStream.distinctUntilChanged()) { _, _ ->
            trigger()
        }
    }

}
