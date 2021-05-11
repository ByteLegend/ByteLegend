package com.bytelegend.app.servershared.dal

import com.fasterxml.jackson.annotation.JsonIgnore
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbIgnore
import software.amazon.awssdk.services.dynamodb.model.AttributeValue

val REPLICABLE_LAST_UPDATED_IN_KEY = "lastUpdatedIn"
val REPLICABLE_LAST_UPDATED_MS_KEY = "lastUpdatedMs"

interface Replicable {
    /**
     * The region name where the record is updated recently, e.g. cn-north-1
     */
    var lastUpdatedIn: String?

    /**
     * Last updated timestamp, in epoch milliseconds.
     */
    var lastUpdatedMs: Long?
}

// This is an adapter layer for application and database layer
// For Player, the sk is "P"
// For mission, the sk is "mapId#M#missionId"
// For states, the sk is "mapId#S"
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | PartitionKey | SortKey                      | server     | map        | x  | y  | answers         | states     |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | gh#alice     | P                            | gh#alice@1 | JavaIsland | 20 | 80 |                 |            |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | gh#alice     |   mapId#M#StarByteLegend     |            |            |    |    | [{star:0, ...}] |            |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | gh#alice     |   mapId#M#XXX                |            |            |    |    | []              |            |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | gh#alice     |       mapId#S                |            |            |    |    |                 | {A:1, B:2} |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
const val PLAYER_SORT_KEY = "P"
const val MISSION_SORT_KEY = "M"

interface StoredInPlayerTable : Replicable {
    var pk: String
    var sk: String

    @get:DynamoDbIgnore
    @get: JsonIgnore
    val primaryKeyMap
        get() = mapOf(
            "pk" to AttributeValue.builder().s(pk).build(),
            "sk" to AttributeValue.builder().s(sk).build()
        )

    @get:DynamoDbIgnore
    @get: JsonIgnore
    val primaryKey
        get() = Key.builder().partitionValue(pk)
            .sortValue(sk)
            .build()
}
