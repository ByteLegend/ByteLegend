package com.bytelegend.app.shared

val newbieVillageLocations =
    (5..14).map { GridCoordinate(it, 70) } +
        (7..14).map { GridCoordinate(it, 71) } +
        (7..14).map { GridCoordinate(it, 72) }

val newbieVillageOldManId = "NewbieVillageOldMan"
val newbieVillageOldManCoordinate = GridCoordinate(15, 71)
val newbieVillageOldManCoordinateAfterMission = GridCoordinate(16, 70)
val newbieVillageOldManCharacterId = "Players_6_7" // old man
val starGradleLegendMissionId = "Star_GradleLegend/GradleLegend"
val prGradleLegend = "PR_GradleLegend/GradleLegend"
val newbieVillageMissionIds = listOf(
    starGradleLegendMissionId,
    prGradleLegend
)

val newbieIslandGuardId = "NewbieIslandGuard"
val newbieIslandGuardCoordinate = GridCoordinate(21, 60)
val newbieIslandGuardCoordinateAfterMission = GridCoordinate(20, 61)
val newbieIslandGuardCharacterId = "Players_3_7" // soilder

val solveEmeraldQuestionMissionId = "Solve_EmeraldQuestion"
val newbieIslandGuardMissionIds = listOf(
    solveEmeraldQuestionMissionId
)

val emeraldBoxId = "EmeraldBox"
val emeraldQuestionRegex = Regex(""".*Gradle 6\..*Build time.*Revision.*""", setOf())
val emeraldMissionSheetId = "Chest"
val crystalSheetId = "Crystal"
val emeraldMissionCoordinate = GridCoordinate(35, 74)

val gradleIslandGuardId = "GradleIslandGuard"
val gradleIslandGuardCharacterId = "Players_3_7"
val gradleIslandGuardCoordinate = GridCoordinate(7, 45)
val gradleIslandGuardCoordinateAfterMission = GridCoordinate(7, 42)
val gradleInitMissionId = "PR_GradleLegend/init-gradle-project"

val enemyId = "Enemy"
val enemyCharacterId = "Players_2_7"
val enemyCoordinate = GridCoordinate(24, 49)