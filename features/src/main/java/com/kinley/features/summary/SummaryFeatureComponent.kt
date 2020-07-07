package com.kinley.features.summary

import com.kinley.features.featurecomponent.FeatureComponent
import com.kinley.features.summary.domain.SummaryItem
import com.kinley.features.summary.presentation.SummaryUiModel
import com.kinley.features.summary.presentation.SummaryUiState
import com.kinley.features.summary.presentation.SummaryView
import com.kinley.features.summary.setup.SummaryEventDispatcher
import com.kinley.features.summary.setup.SummaryEventReceiver
import com.kinley.features.summary.setup.SummaryUiDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SummaryFeatureComponent(
    override val eventDispatcher: SummaryEventDispatcher
) : FeatureComponent<SummaryView, SummaryEventDispatcher>, SummaryEventReceiver, SummaryUiDelegate,
    CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    private val vm: SummaryViewModel = SummaryViewModel()
    private val uiState: Flow<SummaryUiState> = vm.state
        .map {
            val summaryUiModels = it.summaryItems.map { summaryItem ->
                SummaryUiModel(
                    summaryItem.drawableId,
                    summaryItem.name,
                    summaryItem.amount
                )
            }
            SummaryUiState(
                summaryUiModels = summaryUiModels,
                totalAmount = it.summaryItems.sumBy { it.amount })
        }

    override fun render(view: SummaryView) {
        view.create(uiState, this)
    }

    override fun onPlaceOrderClick() {
        eventDispatcher.confirmBookingClick()
    }

    override fun updateSummaryItems(hashMap: Map<String, SummaryItem>) {
        vm.updateItems(hashMap.values.toList())
    }

}
