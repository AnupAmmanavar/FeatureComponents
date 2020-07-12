package com.kinley.flightbooking

import android.os.Build
import com.kinley.features.flights.FlightFeatureComponent
import com.kinley.features.flights.domain.Flight
import com.kinley.features.flights.setup.FlightEventDispatcher
import com.kinley.features.hotels.HotelFeatureComponent
import com.kinley.features.hotels.domain.Hotel
import com.kinley.features.hotels.setup.HotelEventDispatcher
import com.kinley.features.summary.SummaryFeatureComponent
import com.kinley.features.summary.domain.SummaryItem
import com.kinley.features.summary.setup.SummaryEventDispatcher
import com.kinley.features.summary.setup.SummaryEventReceiver
import java.util.*
import kotlin.collections.HashMap

class MainViewModel {

    val hotelComponent = HotelFeatureComponent(eventDispatcher = HotelEventDispatchListener())
    val flightComponent = FlightFeatureComponent(eventDispatcher = FlightEventDispatchListener())
    val summaryComponent = SummaryFeatureComponent(eventDispatcher = SummaryEventDispatchListener())

    private val summaryEventReceiver: SummaryEventReceiver = summaryComponent

    private val summaryItems: HashMap<String, SummaryItem> = HashMap()

    inner class HotelEventDispatchListener : HotelEventDispatcher {
        override fun onHotelSelected(hotel: Hotel) {
            updateSummary(SummaryItem("hotel", R.drawable.icon_hotel, hotel.name, hotel.cost))
        }
    }

    inner class FlightEventDispatchListener : FlightEventDispatcher {
        override fun onFlightSelection(flight: Flight) {
            updateSummary(SummaryItem("flight", R.drawable.icon_flight, flight.flightName, flight.cost))
        }

    }

    inner class SummaryEventDispatchListener: SummaryEventDispatcher {
        override fun confirmBookingClick() {

        }

        override fun removeClickListener(identifier: String) {
            when (identifier) {
                "hotel" -> hotelComponent.onRemoveSelection()
                "flight" -> flightComponent.onRemoveSelection()
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                summaryItems.keys.removeIf {
                    identifier == it
                }
            }
        }

    }

    private fun updateSummary(summaryItem: SummaryItem) {
        summaryItems[summaryItem.identifier] = summaryItem
        summaryEventReceiver.updateSummaryItems(summaryItems)
    }

    fun onDateChanged(date: Date) {
        hotelComponent.onDateChange(date)
    }

}
