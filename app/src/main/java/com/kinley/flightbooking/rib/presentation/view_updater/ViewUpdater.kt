package com.kinley.flightbooking.rib.presentation.view_updater

import com.kinley.flightbooking.rib.presentation.Presenter
import com.kinley.flightbooking.rib.presentation.VMMapper
import com.kinley.flightbooking.rib.state_reducer.StateReducer
import com.kinley.flightbooking.rib.state_reducer.state_provider.StateProvider

abstract class ViewUpdater<S, VM>(
    private val presenter: Presenter<VM>,
    private val stateProvider: StateProvider<S>,
    private val vmMapper: VMMapper<S, VM>
)  {

    fun trigger() {
        val vm = vmMapper.map(state = stateProvider.getState())
        presenter.render(vm)
    }

    /**
     * Defines the condition for triggering the View changes.
     */
    abstract fun observe()

}
