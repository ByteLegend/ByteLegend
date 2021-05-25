package com.bytelegend.app.shared.entities.mission

import com.bytelegend.app.shared.annotations.JsonIgnore

/**
 * Upon mission completion, update states or items.
 */
data class OnFinishSpec(
    val items: OnFinishItemsChange = OnFinishItemsChange(),
    val states: OnFinishStatesChange = OnFinishStatesChange()
) {
    @JsonIgnore
    fun isEmpty() = items.isEmpty() && states.isEmpty()
}

data class OnFinishItemsChange(
    val add: List<String> = emptyList(),
    val remove: List<String> = emptyList()
) {
    @JsonIgnore
    fun isEmpty() = add.isEmpty() && remove.isEmpty()
}

data class OnFinishStatesChange(
    val put: Map<String, String> = emptyMap(),
    val remove: List<String> = emptyList()
) {
    @JsonIgnore
    fun isEmpty() = put.isEmpty() && remove.isEmpty()
}
