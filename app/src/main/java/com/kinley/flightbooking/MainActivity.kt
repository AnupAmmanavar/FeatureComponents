package com.kinley.flightbooking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kinley.features.hotels.presentation.HotelView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val vm = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm.hotelComponent.render(hotel_view)

        vm.onDateChanged(Date())

    }
}
