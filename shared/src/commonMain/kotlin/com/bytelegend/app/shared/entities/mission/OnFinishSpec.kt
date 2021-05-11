package com.bytelegend.app.shared.entities.mission

/**
 * Upon mission completion, update states or items.
 */
data class OnFinishSpec(
    val items: OnFinishItemsChange?,
    val states: OnFinishStatesChange?
)

data class OnFinishItemsChange(
    val add: List<String> = emptyList(),
    val remove: List<String> = emptyList()
)

data class OnFinishStatesChange(
    val put: Map<String, String> = emptyMap(),
    val remove: List<String> = emptyList()
)
