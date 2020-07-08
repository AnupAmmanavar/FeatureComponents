package com.kinley.flightbooking

import android.util.Log
import com.kinley.features.flights.FlightFeatureComponent
import com.kinley.features.flights.domain.Flight
import com.kinley.features.flights.setup.FlightEventDispatcher
import com.kinley.features.hotels.HotelFeatureComponent
import com.kinley.features.hotels.HotelViewModel
import com.kinley.features.hotels.domain.Hotel
import com.kinley.features.hotels.setup.HotelEventDispatcher
import com.kinley.features.summary.SummaryFeatureComponent
import com.kinley.features.summary.domain.SummaryItem
import com.kinley.features.summary.setup.SummaryEventDispatcher
import com.kinley.features.summary.setup.SummaryEventReceiver
import java.util.*
import kotlin.collections.HashMap

class MainViewModel : HotelEventDispatcher, FlightEventDispatcher, SummaryEventDispatcher {

    val hotelComponent: HotelFeatureComponent =
        HotelFeatureComponent(
            eventDispatcher = this,
            vm = HotelViewModel()
        )

    val summaryItems: HashMap<String, SummaryItem> = HashMap()

    val flightComponent: FlightFeatureComponent = FlightFeatureComponent(eventDispatcher = this)

    val summaryComponent: SummaryFeatureComponent = SummaryFeatureComponent(this)
    val summaryEventReceiver: SummaryEventReceiver = summaryComponent

    override fun onHotelSelected(hotel: Hotel) {
        updateSummary("hotel", SummaryItem(R.drawable.ic_baseline_hotel_24, "Hotel ${hotel.name}", hotel.cost))
    }

    override fun onFlightSelection(flight: Flight) {
        updateSummary("flight", SummaryItem(R.drawable.ic_baseline_flight_24, "Flight ${flight.flightName}", flight.cost))
    }

    private fun updateSummary(identifier: String, summaryItem: SummaryItem) {
        summaryItems[identifier] = summaryItem
        summaryEventReceiver.updateSummaryItems(summaryItems)
    }

    override fun confirmBookingClick() {

    }

    fun onDateChanged(date: Date) {
        hotelComponent.onDateChange(date)
    }

}
