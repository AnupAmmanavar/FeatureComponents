package com.kinley.features.flights.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.kinley.features.FlightViewBindingModel_
import com.kinley.features.R
import com.kinley.features.flights.setup.FlightUiDelegate
import kotlinx.android.synthetic.main.flight_view.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FlightView @JvmOverloads constructor(
    context: Context? = null,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle), CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    init {
        View.inflate(context, R.layout.flight_view, this)
    }

    private var ui: FlightsUiState = FlightsUiState()

    fun create(uiStateFlow: StateFlow<FlightsUiState>, uiDelegate: FlightUiDelegate) {

        rv_flights.withModels {
            ui.flightsUiModel.forEach { flightUiModel ->
                FlightViewBindingModel_()
                    .id(flightUiModel.airline)
                    .name(flightUiModel.flightName)
                    .addTo(this)
            }
        }


        launch {
            uiStateFlow.collect {
                ui = it
                rv_flights.requestModelBuild()
            }
        }
    }

}
