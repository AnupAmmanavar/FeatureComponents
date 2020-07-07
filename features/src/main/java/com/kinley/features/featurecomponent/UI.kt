package com.kinley.features.featurecomponent

import android.view.ViewGroup

interface UI<V : ViewGroup> {
    fun render(view: V)
}
