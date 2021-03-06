@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.flux

import kotlinx.coroutines.flow.MutableStateFlow

interface Reducer<T> {
    var state: MutableStateFlow<T>
    fun reduce(t: T)  {
        state.value = t
    }
}

fun <T> Reducer<T>.setState(block: T.() -> T) {
    reduce(block(state.value))
}

/*fun <T> Reducer<T>.setState(block: T.() -> T) {
    state.value
        .run { if (this is Async.Success) this else null }
        ?.let {
            reduce(Async.Success(it.t.block()))
        }

}*/
