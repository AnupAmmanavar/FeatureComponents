@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.ext

import com.kinley.features.featurecomponent.flux.Async
import kotlinx.coroutines.flow.*

fun <T> StateFlow<Async<T>>.filterSuccess(): Flow<T> {
    return distinctUntilChanged { old, new -> old == new }
        .filterIsInstance<Async.Success<T>>()
        .map { it.t }
}

