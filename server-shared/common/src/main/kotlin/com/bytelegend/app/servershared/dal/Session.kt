/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bytelegend.app.servershared.dal

import software.amazon.awssdk.enhanced.dynamodb.extensions.annotations.DynamoDbVersionAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbIgnore
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import java.time.Duration
import java.time.Instant

private val DEFAULT_SESSION_AGE = Duration.ofDays(14)
private val DEFAULT_SESSION_RENEW = Duration.ofDays(7)
const val SESSION_COOKIE_NAME = "BYTELEGEND_SESSION"
const val SESSION_TABLE = "session"

@DynamoDbBean
open class Session : Mvcc {
    @get: DynamoDbPartitionKey
    var id: String = ""
    var playerId: String = ""

    @get: DynamoDbVersionAttribute
    override var version: Int? = null

    var createdAt: Instant = Instant.now()
    var data: MutableMap<String, String> = HashMap()

    @get: DynamoDbIgnore
    val isRenewable: Boolean
        get() = createdAt.plus(DEFAULT_SESSION_RENEW) < Instant.now()

    @get: DynamoDbIgnore
    val isExpired: Boolean
        get() = expiredAt < Instant.now().epochSecond

    // Epoch seconds
    var expiredAt: Long = createdAt.plus(DEFAULT_SESSION_AGE).epochSecond

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Session

        if (id != other.id) return false

        return true
    }

    companion object {
        fun create(id: String, playerId: String) = Session().apply {
            this.id = id
            this.playerId = playerId
        }
    }
}
