package com.kinley.features.summary.presentation

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.kinley.features.summary.setup.SummaryUiDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class SummaryView @JvmOverloads constructor(
    context: Context? = null,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle), CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    fun create(uiState: Flow<SummaryUiState>, uiDelegate: SummaryUiDelegate) {

    }
}
