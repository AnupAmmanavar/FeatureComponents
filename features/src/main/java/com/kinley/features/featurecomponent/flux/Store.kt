@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.featurecomponent.flux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface Store<T, A: Action> {

    fun stateStream(): StateFlow<T>

    fun dispatchActions(action: A)

}
