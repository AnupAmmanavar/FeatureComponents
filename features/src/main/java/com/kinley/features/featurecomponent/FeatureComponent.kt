package com.kinley.features.featurecomponent

import android.view.ViewGroup
import kotlinx.coroutines.CoroutineScope

interface FeatureComponent<V : ViewGroup, E : EventDispatcher> : UI<V>, CoroutineScope {
    val eventDispatcher: E
}
