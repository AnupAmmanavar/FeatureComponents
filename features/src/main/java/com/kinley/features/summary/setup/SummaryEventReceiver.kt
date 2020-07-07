package com.kinley.features.summary.setup

import com.kinley.features.featurecomponent.EventReceiver
import com.kinley.features.summary.domain.SummaryItem

interface SummaryEventReceiver: EventReceiver {
    fun updateSummaryItems(hashMap: Map<String, SummaryItem>)
}
