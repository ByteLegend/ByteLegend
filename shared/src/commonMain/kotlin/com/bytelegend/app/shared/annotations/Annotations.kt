package com.bytelegend.app.shared.annotations

expect annotation class JsonIgnore()
expect annotation class JsonCreator()
expect annotation class DynamoDbBean()
expect annotation class DynamoDbSecondaryPartitionKey(val indexNames: Array<String>)
expect annotation class DynamoDbPartitionKey()
expect annotation class DynamoDbSortKey()
expect annotation class DynamoDbIgnore()
expect annotation class DynamoDbVersionAttribute()

