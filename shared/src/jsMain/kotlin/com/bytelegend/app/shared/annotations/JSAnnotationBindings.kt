package com.bytelegend.app.shared.annotations

actual annotation class JsonIgnore()
actual annotation class JsonCreator()
actual annotation class DynamoDbBean()
actual annotation class DynamoDbPartitionKey()
actual annotation class DynamoDbSortKey()
actual annotation class DynamoDbIgnore()
actual annotation class DynamoDbVersionAttribute()
actual annotation class DynamoDbSecondaryPartitionKey(actual val indexNames: Array<String>)
actual annotation class ReadOnly()


