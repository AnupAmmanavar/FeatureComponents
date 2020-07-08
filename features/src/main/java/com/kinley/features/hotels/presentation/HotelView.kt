@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.hotels.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.kinley.features.HotelViewBindingModel_
import com.kinley.features.R
import com.kinley.features.hotels.setup.HotelUiDelegate
import kotlinx.android.synthetic.main.hotel_view.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HotelView @JvmOverloads constructor(
    context: Context? = null,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle), CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    private lateinit var ui: HotelUiState

    init { View.inflate(context, R.layout.hotel_view, this) }

    fun create(uiState: Flow<HotelUiState>, uiDelegate: HotelUiDelegate) {

        rv_hotels.withModels {

            if (!this@HotelView::ui.isInitialized) return@withModels

            ui.hotels.onEach { hotel ->
                HotelViewBindingModel_()
                    .id(hotel.id)
                    .name(hotel.name)
                    .cost(hotel.cost)
                    .isSelected(hotel.name == ui.selectedHotel?.name)
                    .onClick { _ ->
                        uiDelegate.onHotelClicked(hotel.id)
                    }
                    .addTo(this)
            }
        }

        uiState
            .onEach {
                ui = it
                rv_hotels.requestModelBuild()
            }
            .launchIn(this)


    }
}
