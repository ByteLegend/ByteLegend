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
    val lastUpdatedIn: String

    /**
     * Last updated timestamp, in epoch milliseconds.
     */
    val lastUpdatedMs: Long
}

// This is an adapter layer for application and database layer
// For Player, the sk is "P"
// For mission, the sk is "M#mapId#missionId"
// For headSha -> pull request, the sk is "H#headSha"
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | PartitionKey | SortKey                      | server     | map        | x  | y  | answers         | states     |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | gh#alice     | P                            | gh#alice@1 | JavaIsland | 20 | 80 |                 |            |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | gh#alice     |   PC#mapId#mission#challenge |            |            |    |    | [{star:0, ...}] |            |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | gh#alice     |   H#5843f14c34f5d0e05        | pullRequest: listOf(repo: github.com/ByteLegend/ByteLegend) number:1234 state:closed merged: true
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
const val PLAYER_SORT_KEY = "P"
const val PLAYER_CHALLENGE_SORT_KEY = "PC"
const val HEAD_SHA_SORT_KEY = "H"

interface StoredInPlayerTable {
    val pk: String
    val sk: String

    @get:DynamoDbIgnore
    @get: JsonIgnore
    val primaryKeyMap
        get() = mapOf(
            "pk" to AttributeValue.builder().s(pk).build(),
            "sk" to AttributeValue.builder().s(sk).build()
        )

    @get:DynamoDbIgnore
    @get: JsonIgnore
    val primaryKey: Key
        get() = Key.builder().partitionValue(pk)
            .sortValue(sk)
            .build()
}
