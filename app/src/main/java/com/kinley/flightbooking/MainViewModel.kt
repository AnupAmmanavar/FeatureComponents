package com.kinley.flightbooking

import com.kinley.features.hotels.HotelFeatureComponent
import com.kinley.features.hotels.HotelViewModel
import com.kinley.features.hotels.domain.Hotel
import com.kinley.features.hotels.setup.HotelEventDispatcher

class MainViewModel : HotelEventDispatcher {

    val hotelComponent: HotelFeatureComponent =
        HotelFeatureComponent(
            eventDispatcher = this,
            vm = HotelViewModel()
        )

    override fun onHotelSelected(hotel: Hotel) {
        TODO("Not yet implemented")
    }

}
