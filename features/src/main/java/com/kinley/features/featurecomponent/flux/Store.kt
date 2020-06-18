@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.featurecomponent.flux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface Store<T, A: Action> {

    val state: MutableStateFlow<T>

    fun stateStream(): StateFlow<T> = state

    fun dispatchActions(action: A)

}
