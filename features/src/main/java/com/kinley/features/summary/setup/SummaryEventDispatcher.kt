package com.kinley.features.summary.setup

import com.kinley.features.featurecomponent.EventDispatcher

interface SummaryEventDispatcher: EventDispatcher {
    fun confirmBookingClick()

    fun removeClickListener(identifier: String)
}
