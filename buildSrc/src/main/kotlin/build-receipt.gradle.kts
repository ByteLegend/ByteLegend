import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Provide a unified build timestamp for consumers, like:
 * - buildReleaseGameResources use the timestamp as destination directory name.
 * - dockerBuild use the timestamp as image tag.
 * - dockerTest use the timestamp-tagged docker image for test.
 */

require(parent == null) {
    "build-receipt must be applied to rootProject!"
}

val buildTimestamp = findProperty("buildTimestamp") ?: DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
    .withZone(ZoneId.systemDefault())
    .format(Instant.now())

extensions.extraProperties.set("buildTimestamp", buildTimestamp)
