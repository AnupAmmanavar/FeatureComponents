package com.kinley.features.featurecomponent.flux

sealed class Async<out T> {
    object Uninitialized : Async<Nothing>()
    data class Loading(val message: String) : Async<Nothing>()
    data class Failure(val e: Throwable) : Async<Nothing>()
    data class Success<out T>(val t: T) : Async<T>()
}
