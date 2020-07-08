package com.kinley.features.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.kinley.features.summary.domain.SummaryItem
import com.kinley.features.summary.presentation.SummaryUiModel

@BindingAdapter("amount")
fun TextView.setAmount(amount: Number) {
    text = "Rs $amount"
}

@BindingAdapter("summaryItem")
fun TextView.setSummaryItem(uiModel: SummaryUiModel) {
    val drawable = context.getDrawable(uiModel.icon)
    drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight);
    val drawables = compoundDrawables
    setCompoundDrawablesWithIntrinsicBounds(drawable, drawables[1], drawables[2], drawables[3])
    text = uiModel.name
}
