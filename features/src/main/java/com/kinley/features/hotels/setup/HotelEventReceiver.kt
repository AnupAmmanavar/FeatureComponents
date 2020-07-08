package com.kinley.features.hotels.setup

import com.kinley.features.featurecomponent.EventReceiver
import java.util.*

interface HotelEventReceiver : EventReceiver {
    fun onDateChange(date: Date)

    fun onRemoveSelection()
}
