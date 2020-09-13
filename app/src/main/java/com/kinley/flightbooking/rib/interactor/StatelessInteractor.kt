package com.kinley.flightbooking.rib.interactor

import com.kinley.flightbooking.rib.state_reducer.Empty
import com.kinley.flightbooking.rib.state_reducer.EmptyStateReducer
import kotlin.coroutines.CoroutineContext

open class StatelessInteractor(
    override val coroutineContext: CoroutineContext
) : StatefulInteractor<Empty>(EmptyStateReducer(), coroutineContext)
