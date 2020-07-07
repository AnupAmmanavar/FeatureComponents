package com.kinley.flightbooking

import android.util.Log
import com.kinley.features.flights.FlightFeatureComponent
import com.kinley.features.flights.domain.Flight
import com.kinley.features.flights.setup.FlightEventDispatcher
import com.kinley.features.hotels.HotelFeatureComponent
import com.kinley.features.hotels.HotelViewModel
import com.kinley.features.hotels.domain.Hotel
import com.kinley.features.hotels.setup.HotelEventDispatcher
import java.util.*

class MainViewModel : HotelEventDispatcher, FlightEventDispatcher {

    val hotelComponent: HotelFeatureComponent =
        HotelFeatureComponent(
            eventDispatcher = this,
            vm = HotelViewModel()
        )

    val flightComponent: FlightFeatureComponent = FlightFeatureComponent(eventDispatcher = this)

    override fun onHotelSelected(hotel: Hotel) {
        Log.d("IdentifierTag", "$hotel")
    }

    override fun onFlightSelection(flight: Flight) {
        Log.d("IdentifierTag", "$flight")
    }

    fun onDateChanged(date: Date) {
        hotelComponent.onDateChange(date)
    }

}
