package com.kinley.flightbooking.rib.presentation

interface VMMapper<S, VM> {
    fun map(state: S): VM
}
