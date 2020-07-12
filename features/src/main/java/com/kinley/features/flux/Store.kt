@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.flux

import kotlinx.coroutines.flow.StateFlow

interface Store<S: AppState, A: Action> {

    fun stateStream(): StateFlow<S>

    fun dispatchActions(action: A)

}
