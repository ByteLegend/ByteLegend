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

import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTag
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticTableMetadata
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.BeanTableSchemaAttributeTag
import java.util.function.Consumer

@Target(AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
@BeanTableSchemaAttributeTag(ReadOnlyAttributeTags::class)
actual annotation class ReadOnly

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
