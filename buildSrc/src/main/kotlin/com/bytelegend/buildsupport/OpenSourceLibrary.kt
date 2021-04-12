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
    GPL("https://www.gnu.org/licenses/gpl-3.0.en.html"),
    BSD("https://en.wikipedia.org/wiki/BSD_licenses"),
    Apache2("https://www.apache.org/licenses/LICENSE-2.0"),
    AGPL3("https://www.gnu.org/licenses/agpl-3.0.en.html"),
    LGPL("https://www.gnu.org/licenses/lgpl-3.0.en.html"),
    MIT("https://en.wikipedia.org/wiki/MIT_License"),
    WTFPL("https://en.wikipedia.org/wiki/WTFPL"),
    GreenSockStanardLicense("https://greensock.com/standard-license/")
}