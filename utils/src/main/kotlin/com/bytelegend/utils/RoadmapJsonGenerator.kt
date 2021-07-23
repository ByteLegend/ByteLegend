package com.bytelegend.utils

data class RoadmapJsonData(
    val nodes: List<RoadmapMissionNode>,
    val links: List<RoadmapMissionLink>,
    val categories: List<RoadmapMissionRegion>
)

data class RoadmapMissionNode(
    val id: String,
    val name: String,
    val symbolSize: Number,
    val x: Int,
    val y: Int,
    val value: Int,
    val category: Int
)

data class RoadmapMissionLink(
    val source: String,
    val target: String,
    // https://github.com/apache/echarts/issues/4062
    val label: Map<String, Any> = mapOf("show" to true, "formatter" to "fuck")
)

data class RoadmapMissionRegion(
    val name: String
)
