package com.kinley.flightbooking

import android.util.Log
import com.kinley.features.hotels.HotelFeatureComponent
import com.kinley.features.hotels.HotelViewModel
import com.kinley.features.hotels.domain.Hotel
import com.kinley.features.hotels.setup.HotelEventDispatcher
import java.util.*

class MainViewModel : HotelEventDispatcher {

    val hotelComponent: HotelFeatureComponent =
        HotelFeatureComponent(
            eventDispatcher = this,
            vm = HotelViewModel()
        )

    override fun onHotelSelected(hotel: Hotel) {
        Log.d("BookingTag", "$hotel")
    }

    fun onDateChanged(date: Date) {
        hotelComponent.onDateChange(date)
    }

}
