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

expect annotation class JsonIgnore()
expect annotation class JsonCreator()
expect annotation class JsonValue()
expect annotation class DynamoDbBean()
expect annotation class DynamoDbSecondaryPartitionKey(val indexNames: Array<String>)
expect annotation class DynamoDbPartitionKey()
expect annotation class DynamoDbSortKey()
expect annotation class DynamoDbIgnore()
expect annotation class DynamoDbVersionAttribute()

/**
 * Indicates a field should not be updated when saving, but should still be read from persistent layer.
 */

const val READ_ONLY_METADATA_KEY = "ReadOnly"

expect annotation class ReadOnly()
