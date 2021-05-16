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

