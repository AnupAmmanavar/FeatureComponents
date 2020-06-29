package com.kinley.features.hotels.setup

import com.kinley.features.hotels.domain.Hotel

interface HotelUiDelegate {
    fun onHotelClicked(hotel: Hotel)
}
