@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.featurecomponent.flux

import kotlinx.coroutines.flow.StateFlow

interface Store<S, A: Action> {

    fun stateStream(): StateFlow<Async<S>>

    fun dispatchActions(action: A)

}
