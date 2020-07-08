package com.kinley.features.summary.presentation

import androidx.annotation.DrawableRes

data class SummaryUiState(
    val summaryUiModels: List<SummaryUiModel>,
    val totalAmount: Int
)

data class SummaryUiModel(
    val identifier: String,
    @DrawableRes val icon: Int,
    val name: String,
    val amount: Int
)
