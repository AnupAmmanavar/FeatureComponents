@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.flux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface Store<State: AppState, A: Action> {

    var state: MutableStateFlow<State>
    fun reduce(s: State)  {
        state.value = s
    }

    fun stateStream(): StateFlow<State>

    fun dispatchActions(action: A)

}


fun <S: AppState> Store<S, *>.setState(reducer: S.() -> S) {
    reduce(reducer(state.value))
}
