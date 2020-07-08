package com.kinley.features.summary.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.kinley.features.R
import com.kinley.features.SummaryItemBindingModel_
import com.kinley.features.summary.setup.SummaryUiDelegate
import kotlinx.android.synthetic.main.summary_view.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SummaryView @JvmOverloads constructor(
    context: Context? = null,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle), CoroutineScope by CoroutineScope(Dispatchers.Main.immediate) {

    init {
        View.inflate(context, R.layout.summary_view, this)
    }

    private var ui: SummaryUiState = SummaryUiState(listOf(), 0)

    fun create(uiState: Flow<SummaryUiState>, uiDelegate: SummaryUiDelegate) {

        btn_book.setOnClickListener { uiDelegate.onPlaceOrderClick() }

        rv_summary_items.withModels {
            ui.summaryUiModels.forEach {
                SummaryItemBindingModel_()
                    .id(it.identifier)
                    .uiModel(it)
                    .cancelClick { _ ->
                        uiDelegate.removeClick(it.identifier)
                    }
                    .addTo(this)
            }
        }

        uiState
            .onEach {
                ui = it

                tv_amount.text = "Rs ${ui.totalAmount}"
                rv_summary_items.requestModelBuild()
            }
            .launchIn(this)


    }
}
