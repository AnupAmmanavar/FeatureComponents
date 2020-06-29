package com.kinley.features.hotels.presentation

import com.kinley.features.hotels.domain.Hotel

data class HotelUiState(
    val hotels: List<HotelUiModel>,
    val selectedHotel: Hotel?
)
