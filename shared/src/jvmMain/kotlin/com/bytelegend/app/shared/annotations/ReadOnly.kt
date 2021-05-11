package com.bytelegend.app.shared.annotations

import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTag
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticTableMetadata
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.BeanTableSchemaAttributeTag
import java.util.function.Consumer

@kotlin.annotation.Target(AnnotationTarget.PROPERTY_GETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@BeanTableSchemaAttributeTag(ReadOnlyAttributeTags::class)
actual annotation class ReadOnly()

class ReadOnlyAttributeTags {
    companion object {
        @Suppress("UNUSED_PARAMETER")
        @JvmStatic
        fun attributeTagFor(annotation: ReadOnly): StaticAttributeTag {
            return StaticAttributeTag { attributeName, attributeValueType ->
                Consumer { metadata: StaticTableMetadata.Builder ->
                    metadata.addCustomMetadataObject("$READ_ONLY_METADATA_KEY:$attributeName", "1")
                        .markAttributeAsKey(attributeName, attributeValueType)
                }
            }
        }
    }
}

