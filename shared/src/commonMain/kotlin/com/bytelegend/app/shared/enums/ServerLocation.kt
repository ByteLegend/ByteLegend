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
package com.bytelegend.app.shared.enums

enum class ServerLocation(
    val awsRegionId: String,
    /**
     * We generate a unique id for each server atomically based on database,
     * to avoid cross-region id conflict, an offset is assigned to the id generator.
     *
     * The gap is calculated by: if we have 100 servers which restarts 10 times per day,
     * then we run out of 10_000_000 after 3 years.
     */
    val serverIdOffset: Int,
    val dynamoDbEndpoint: String
) {
    Beijing(
        "cn-north-1",
        0,
        "https://dynamodb.cn-north-1.amazonaws.com.cn"
    ),
    Seoul(
        "ap-northeast-2",
        1_000_000,
        "https://dynamodb.ap-northeast-2.amazonaws.com"
    );

    fun displayNameId() = "${name}ServerDisplayNameId"
}
