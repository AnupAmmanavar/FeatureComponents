package com.kinley.features.hotels

import com.kinley.features.featurecomponent.FeatureComponent
import com.kinley.features.hotels.presentation.HotelUiModel
import com.kinley.features.hotels.presentation.HotelUiState
import com.kinley.features.hotels.presentation.HotelView
import com.kinley.features.hotels.setup.HotelEventDispatcher
import com.kinley.features.hotels.setup.HotelEventReceiver
import com.kinley.features.hotels.setup.HotelUiDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.util.*

@Suppress("EXPERIMENTAL_API_USAGE")
class HotelFeatureComponent(
    override val eventDispatcher: HotelEventDispatcher
) : FeatureComponent<HotelView, HotelEventDispatcher>, HotelEventReceiver, HotelUiDelegate, CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    private val vm: HotelViewModel = HotelViewModel()

    private val uiState: Flow<HotelUiState> =
        combine(vm.hotels, vm.hotelSelected) { hotels, selectedHotel ->
            HotelUiState(
                hotels = hotels.map { HotelUiModel(it.identifier, it.name, it.cost) },
                selectedHotel = selectedHotel
            )
        }

    override fun render(view: HotelView) {
        view.create(uiState, this)
    }

    /**
     * This is the event sent by the parent. The component passes this  event
     * to it's business-logic layer [HotelViewModel]
     */
    override fun onDateChange(date: Date) {
        vm.dateChange(date)
    }

    override fun onRemoveSelection() {
        vm.removeSelectedHotel()
    }

    /**
     * Notify the business-logic layer of the FeatureComponent i.e [HotelViewModel] and
     * also dispatches the event to the outside world.
     */
    override fun onHotelClicked(id: String) {
        vm.updateSelectedHotel(id)
        val hotel = vm.hotelSelected.value ?: return

        // Propagate the event to the outside world
        eventDispatcher.onHotelSelected(hotel)
    }

}
