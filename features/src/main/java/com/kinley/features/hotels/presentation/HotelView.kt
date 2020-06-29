@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.features.hotels.presentation

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.kinley.features.hotels.setup.HotelUiDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HotelView(context: Context? = null, attributeSet: AttributeSet? = null, defStyle: Int = 0) :
    ConstraintLayout(context, attributeSet, defStyle),
    CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    fun create(uiState: Flow<HotelUiState>, uiDelegate: HotelUiDelegate) {
        uiState
            .onEach {
                // update with new UiState
            }
            .launchIn(this)

    }
}
