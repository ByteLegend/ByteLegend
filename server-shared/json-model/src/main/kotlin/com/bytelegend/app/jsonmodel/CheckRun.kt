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
package com.bytelegend.app.jsonmodel

import com.fasterxml.jackson.annotation.JsonProperty

// https://docs.github.com/en/rest/reference/checks#create-a-check-run
data class CheckRun(
    val name: String,
    @JsonProperty("head_sha")
    val headSha: String,
    @JsonProperty("details_url")
    val detailsUrl: String,
    @JsonProperty("external_id")
    val externalId: String,
    val status: String,
    @JsonProperty("started_at")
    val startedAt: String,
    val conclusion: String,
    @JsonProperty("completed_at")
    val completedAt: String,
    val output: CheckRunOutput
)

data class CheckRunOutput(
    val title: String,
    val summary: String,
    val text: String,
    val annotations: List<CheckRunOutputAnnotation>,
    val images: List<CheckRunOutputImage>
)

data class CheckRunOutputAnnotation(
    val path: String,
    @JsonProperty("start_line")
    val startLine: Int,
    @JsonProperty("end_line")
    val endLine: Int,
    @JsonProperty("start_column")
    val startColumn: Int,
    @JsonProperty("end_column")
    val endColumn: Int,
    @JsonProperty("annotation_level")
    val annotationLevel: String,
    val message: String,
    val title: String,
    @JsonProperty("raw_details")
    val rawDetails: String
)

data class CheckRunOutputImage(
    val alt: String,
    @JsonProperty("image_url")
    val imageUrl: String,
    val caption: String,
    val actions: List<CheckRunOutputImageAction>
)

data class CheckRunOutputImageAction(
    val label: String,
    val description: String,
    val identifier: String
)
