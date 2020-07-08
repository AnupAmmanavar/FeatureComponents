package com.kinley.features.summary

import android.os.Build
import androidx.annotation.RequiresApi
import com.kinley.features.summary.domain.SummaryItem
import com.kinley.features.summary.domain.SummaryState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Suppress("EXPERIMENTAL_API_USAGE")
class SummaryViewModel : CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    private val _mutableSummaryItems = MutableStateFlow(SummaryState.initState())
    val state: StateFlow<SummaryState> = _mutableSummaryItems

    fun updateItems(items: List<SummaryItem>) {
        _mutableSummaryItems.value = SummaryState(items, items.sumBy { it.amount })
    }

    fun removeItem(identifier: String) {
        val summaryItemsMap= _mutableSummaryItems.value.summaryItems as MutableList
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            summaryItemsMap.removeIf { it.identifier == identifier }
        }
        updateItems(summaryItemsMap)
    }
}
