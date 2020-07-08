@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.hotels

import android.util.Log
import com.kinley.features.featurecomponent.EventReceiver
import com.kinley.features.hotels.domain.Hotel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class HotelViewModel : EventReceiver {

    private val repository = HotelRepository()

    private val _hotelsMutable: MutableStateFlow<List<Hotel>> = MutableStateFlow(arrayListOf())
    val hotels: StateFlow<List<Hotel>> = _hotelsMutable

    private val _hotelSelectedMutable: MutableStateFlow<Hotel?> = MutableStateFlow(null)
    val hotelSelected: StateFlow<Hotel?> = _hotelSelectedMutable

    init {
        fetchHotels(Date())
    }

    private fun fetchHotels(date: Date) {
        _hotelsMutable.value = repository.getHotels(date)
    }

    fun dateChange(date: Date) {
        Log.d("BookingTag", "$date")
    }

    fun updateSelectedHotel(id: String) {
        _hotelSelectedMutable.value = _hotelsMutable.value.first {
            it.identifier == id
        }
    }

}


class HotelRepository {

    fun getHotels(date: Date): List<Hotel> {
        val hotelNames = listOf("Brindavan", "Mandara", "Elivs", "Orlis", "Karle", "Krishna Sagar", "Orion")
        return hotelNames.map {
            Hotel(UUID.randomUUID().toString(), it, DataFactory.provideInt(), "Dharwad")
        }
    }

}

object DataFactory {
    fun provideInt(): Int = ThreadLocalRandom.current().nextInt(1000, 2000);
}
