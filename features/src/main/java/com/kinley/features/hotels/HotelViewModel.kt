@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.hotels

import android.util.Log
import com.kinley.features.featurecomponent.EventReceiver
import com.kinley.features.hotels.domain.Hotel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*

class HotelViewModel : EventReceiver {

    private val _hotelsMutable: MutableStateFlow<List<Hotel>> = MutableStateFlow(arrayListOf())
    val hotels: StateFlow<List<Hotel>> = _hotelsMutable

    private val _hotelSelectedMutable: MutableStateFlow<Hotel?> = MutableStateFlow(null)
    val hotelSelected: StateFlow<Hotel?> = _hotelSelectedMutable

    fun dateChange(date: Date) {
        Log.d("BookingTag", "$date")
    }

    fun hotelSelected(hotel: Hotel) {

    }

}
