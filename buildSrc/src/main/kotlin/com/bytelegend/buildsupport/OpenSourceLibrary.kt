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
package com.bytelegend.buildsupport

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable

data class OpenSourceLibrary(
    @JsonIgnore
    val name: String = "",
    @JsonIgnore
    val groupArtifactId: String = "",
    @JsonIgnore
    val version: String = "",
    /**
     * If creditName is specified, it will be displayed at "Credits" page
     */
    val creditName: String = "",
    val url: String = "",
    val license: OpenSourceLicense? = null
) : Serializable {
    constructor(creditName: String, url: String, license: OpenSourceLicense) :
        this(
            "", "", "",
            creditName, url, license
        )

    constructor(gav: String) : this(
        gav.split(":")[1],
        gav.substringBeforeLast(":"),
        gav.substringAfterLast(":")
    )

    val gav: String
        @JsonIgnore
        get() = "$groupArtifactId:$version"
    val licenseUrl: String?
        get() = license?.url
}

enum class OpenSourceLicense(val url: String) {
    Free(""),
    GPL("https://www.gnu.org/licenses/gpl-3.0.en.html"),
    BSD("https://en.wikipedia.org/wiki/BSD_licenses"),
    BSD2Clause("https://opensource.org/licenses/BSD-2-Clause"),
    Apache2("https://www.apache.org/licenses/LICENSE-2.0"),
    LGPL("https://www.gnu.org/licenses/lgpl-3.0.en.html"),
    MIT("https://en.wikipedia.org/wiki/MIT_License"),
    EDL("https://www.eclipse.org/org/documents/edl-v10.php"),
    ISC("https://en.wikipedia.org/wiki/ISC_license"),
    WTFPL("https://en.wikipedia.org/wiki/WTFPL"),
    GreenSockStandardLicense("https://greensock.com/standard-license/")
}
