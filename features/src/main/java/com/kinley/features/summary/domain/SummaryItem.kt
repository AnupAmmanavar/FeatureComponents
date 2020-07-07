package com.kinley.features.summary.domain

import androidx.annotation.DrawableRes

data class SummaryItem(
    @DrawableRes val drawableId: Int,
    val name: String,
    val amount: Int
)

data class SummaryState(
    val summaryItems: List<SummaryItem>,
    val totalAmount: Int
) {
    companion object {
        fun initState() = SummaryState(listOf(), 0)
    }
}
