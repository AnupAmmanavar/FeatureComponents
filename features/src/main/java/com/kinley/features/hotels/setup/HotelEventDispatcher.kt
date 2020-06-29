package com.kinley.features.hotels.setup

import com.kinley.features.featurecomponent.EventDispatcher
import com.kinley.features.hotels.domain.Hotel

interface HotelEventDispatcher: EventDispatcher {
    fun onHotelSelected(hotel: Hotel)
}
