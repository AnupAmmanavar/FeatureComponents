package com.kinley.features.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("amount")
fun TextView.setAmount(amount: Number) {
    text = "Rs $amount"
}
