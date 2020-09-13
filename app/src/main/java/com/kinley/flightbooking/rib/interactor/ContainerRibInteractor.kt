package com.kinley.flightbooking.rib.interactor

import com.kinley.flightbooking.rib.state_reducer.AbstractStateReducer
import com.kinley.flightbooking.rib.usage.sample1.state.EarningsState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class ContainerRibInteractor<S>(
    stateReducer: ContainerStateReducer<S>,
    coroutineContext: CoroutineContext
) : StatefulInteractor<ContainerRibState<S>>(stateReducer, coroutineContext)

val containerStateReducer: ContainerStateReducer<EarningsState> = ContainerStateReducer(ContainerRibState.Loading("Loading..."), Dispatchers.Default)


class ContainerStateReducer<S>(
    initialState: ContainerRibState<S>,
    stateDispatcher: CoroutineDispatcher
) : AbstractStateReducer<ContainerRibState<S>>(initialState, stateDispatcher) {

    suspend fun setLoading(message: String) = updateState {
        ContainerRibState.Loading(message)
    }

    suspend fun setError(e: Throwable) = updateState {
        ContainerRibState.Error(e)
    }

    suspend fun setData(data: S) = updateState {
        ContainerRibState.Data(data)
    }
}

sealed class ContainerRibState<T> {
    class Loading<T>(val message: String) : ContainerRibState<T>()
    class Data<T>(val data: T) : ContainerRibState<T>()
    class Error<T>(val error: Throwable) : ContainerRibState<T>()
}
