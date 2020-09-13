package com.kinley.flightbooking.rib.presentation

/**
 * Presenters of the all RIBS extend this
 */
interface Presenter<VM> {
    fun render(vm: VM)
}
