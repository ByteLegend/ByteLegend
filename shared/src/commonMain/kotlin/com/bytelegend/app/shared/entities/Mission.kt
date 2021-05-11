//package com.bytelegend.app.shared.entities
//
//import com.bytelegend.app.shared.entities.MissionType.None
//
//val NON_CHALLENGE_MISSION = MissionSpec(None, 0, "")
//
//data class Mission(
//    val id: String,
//    val title: String,
//    val children: List<Mission> = emptyList(),
//    val tutorials: List<Tutorial> = emptyList(),
//    val discussions: String = "",
//    /**
//     * If true, this mission will be displayed in roadmap
//     * as a "checkpoint".
//     *
//     * Default true.
//     */
//    val checkpoint: Boolean = true,
//    val spec: MissionSpec = NON_CHALLENGE_MISSION,
//    val next: String?
//)
//
//class MissionSpec(
//    val type: MissionType,
//    val star: Int,
//    /**
//     * Type == Star, PR -> repo address
//     * Type == Question -> correct answer regex
//     */
//    val spec: String,
//)
//
//enum class MissionType {
//    // It has no challenges.
//    None,
//
//    // Star a GitHub repository
//    Star,
//
//    RememberBravePeople,
//
//    // Create a PR
//    PR,
//
//    // Answer a question
//    Question
//}
//
//fun Mission.flatten(): List<Mission> {
//    val ret = mutableListOf(this)
//    children.forEach {
//        ret.addAll(it.flatten())
//    }
//    return ret
//}
//
