package com.kinley.features.flights

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.kinley.features.flights.domain.Flight
import kotlinx.android.synthetic.main.flight_view.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class FlightView(context: Context? = null, attributeSet: AttributeSet? = null, defStyle: Int = 0) :
    ConstraintLayout(context, attributeSet, defStyle), CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    fun create(uiStateFlow: StateFlow<FlightsUiState>, uiDelegate: FlightUiDelegate) {

        btn_nextday.setOnClickListener {
            uiDelegate.flightClick(Flight("Flight", "Indigo"))
        }

        launch {
            uiStateFlow.collect { recomposeView(it) }
        }
    }

    private fun recomposeView(uiState: FlightsUiState) {

    }

}
