@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.hotels.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.kinley.features.HotelViewBindingModel_
import com.kinley.features.R
import com.kinley.features.hotels.domain.Hotel
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
) :
    ConstraintLayout(context, attributeSet, defStyle),
    CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    private var hotels: List<HotelUiModel> = listOf()

    init {
        View.inflate(context, R.layout.hotel_view, this)
    }

    fun create(uiState: Flow<HotelUiState>, uiDelegate: HotelUiDelegate) {
        uiState
            .onEach {
                // update with new UiState
            }
            .launchIn(this)

        tv_header.setOnClickListener {
            uiDelegate.onHotelClicked(Hotel("Brindavan", 200, "Dharwad"))
        }

        rv_hotels.withModels {

            hotels.onEach {
                HotelViewBindingModel_()
                    .id(it.name)
                    .name(it.name)
                    .cost(it.cost)
                    .addTo(this)
            }
        }

        uiState
            .onEach {
                hotels = it.hotels
                rv_hotels.requestModelBuild()
            }
            .launchIn(this)


    }
}
