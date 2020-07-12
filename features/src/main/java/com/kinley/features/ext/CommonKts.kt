package com.kinley.features.ext

val <T> T.exhaustive: T
    get() = this

// Hook does nothing
fun Ignore() {

}
