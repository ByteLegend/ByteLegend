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
package com.bytelegend.app.shared.annotations

actual typealias DynamoDbBean = software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
actual typealias DynamoDbPartitionKey = software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
actual typealias DynamoDbVersionAttribute = software.amazon.awssdk.enhanced.dynamodb.extensions.annotations.DynamoDbVersionAttribute
actual typealias DynamoDbSortKey = software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey
actual typealias DynamoDbIgnore = software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbIgnore
actual typealias JsonIgnore = com.fasterxml.jackson.annotation.JsonIgnore
actual typealias JsonCreator = com.fasterxml.jackson.annotation.JsonCreator
actual typealias JsonValue = com.fasterxml.jackson.annotation.JsonValue
actual typealias DynamoDbSecondaryPartitionKey = software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey
