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
import com.bytelegend.buildsupport.OpenSourceLibrary
import com.bytelegend.buildsupport.OpenSourceLicense.Apache2
import com.bytelegend.buildsupport.OpenSourceLicense.BSD2Clause
import com.bytelegend.buildsupport.OpenSourceLicense.EDL
import com.bytelegend.buildsupport.OpenSourceLicense.GreenSockStandardLicense
import com.bytelegend.buildsupport.OpenSourceLicense.ISC
import com.bytelegend.buildsupport.OpenSourceLicense.LGPL
import com.bytelegend.buildsupport.OpenSourceLicense.MIT
import com.bytelegend.buildsupport.OpenSourceLicense.WTFPL
import com.bytelegend.buildsupport.OpenSourceLicense.Free

val forestVersion = "0.3.17-SNAPSHOT"
val vertxVersion = "4.2.6"
val guavaVersion = "31.1-jre"
val jacksonVersion = "2.13.2"
val kotlinVersion = "1.7.10"
val coroutinesVersion = "1.6.4"
val junitVersion = "5.8.2"
val junit4Version = "4.13.2"
val mockKVersion = "1.11.0"
val apiGuadianVersion = "1.1.0"
val logbackVersion = "1.2.3"
val slf4jVersion = "1.7.30"
val junitExtensionsVersion = "2.4.0"
val commonsLangVersion = "3.10"
val commonsExecVersion = "1.3"
val commonsIOVersion = "2.7"
val annotationMagicVersion = "0.2.5"
val jsr311ApiVersion = "1.1.1"
val gebVersion = "3.4"
val seleniumVersion = "3.141.59"
val findBugsAnnotationVersion = "3.0.1"
val mockitoVersion = "3.5.10"
val apacheCommonsCodecVersion = "1.10"
val postgreVersion = "42.2.18"
val hikariVersion = "3.4.5"
val javaJwtVersion = "3.11.0"
val bouncycastleVersion = "1.59"
val awsSdkV1Version = "1.11.978"
val awsSdkV2Version = "2.17.160"
val opencc4jVersion = "1.6.0"
val kotlinxSerializationVersion = "1.0.1"
val dynamodbCrossRegionReplicationVersion = "1.2.1.19"
val caffeineVersion = "2.8.8"
val testContainersVersion = "1.17.1"
val kodeinVersion = "7.6.0"
val kotlinReactVersion = "18.2.0-pre.382"
val reactVersion = "18.0.0"
val reactBootstrapVersion = "1.6.1"
val materialUIVersion = "4.11.2"
val kotlinBrowserVersion = "0.2"
val reactTransitionGroupVersion = "4.4.1"
val inBrowserDownloadVersion = "2.0.0"
val getImagePixelsVersion = "1.0.1"
val reactIconsVersion = "3.11.0"
val bootstrapSwitchButtonReactVersion = "1.2.0"
val kmongoVersion = "4.2.4"
val mongodbJavaDriverVersion = "4.2.0"
val batikVersion = "1.14"
val googleTranslationSdkVersion = "19.2.1"
val reactPlayerVersion = "2.9.0"
val reactSelectVersion = "4.3.0"
val commonmarkVersion = "0.18.1"
val reactTextAreaAutosizeVersion = "8.3.2"
// for sync-server because it useus JCL underlying
val jclOverSlf4JVersion = "1.7.30"
val logbackCloudWatchAppenderVersion = "1.0.6.3"
val javaOptVersion = "0.2.0"
val canvasConfettiVersion = "1.4.0"
val springCoreVersion = "5.3.18"
val javaDiffUtilsVersion = "4.9"
val reactMarkdownVersion = "7.0.1"
val rehypeRawVersion = "6.1.0"
val rehypeExternalLinksVersion = "1.0.0"
val remarkGfmVersion = "2.0.0"
val unifiedDiffParserVersion = "1.4"
val jgitVersion = "5.13.0.202109080827-r"
val checkstyleVersion = "8.32"
val hub4jGitHubApiVersion = "1.301"
val animatedGifLibVersion = "1.4"

val dependencies: List<OpenSourceLibrary> = listOf(
    OpenSourceLibrary("core-kotlin", "io.forestframework:core-kotlin", forestVersion),
    OpenSourceLibrary("core", "io.forestframework:core", forestVersion),
    OpenSourceLibrary("pg-client-extension", "io.forestframework:pg-client-extension", forestVersion),
    OpenSourceLibrary("junit5-extension", "io.forestframework:junit5-extension", forestVersion),

    OpenSourceLibrary("io.vertx:vertx-core:$vertxVersion"),
    OpenSourceLibrary("io.vertx:vertx-web:$vertxVersion"),
    OpenSourceLibrary("io.vertx:vertx-lang-kotlin-coroutines:$vertxVersion"),
    OpenSourceLibrary("io.vertx:vertx-lang-kotlin:$vertxVersion"),
    OpenSourceLibrary("io.vertx:vertx-unit:$vertxVersion"),
    OpenSourceLibrary("io.vertx:vertx-codegen:$vertxVersion"),
    OpenSourceLibrary("io.vertx:vertx-redis-client:$vertxVersion"),
    OpenSourceLibrary("io.vertx:vertx-jdbc-client:$vertxVersion"),
    OpenSourceLibrary("io.vertx:vertx-pg-client:$vertxVersion"),
    OpenSourceLibrary("io.vertx:vertx-web-client:$vertxVersion"),
    OpenSourceLibrary("io.vertx:vertx-zookeeper:$vertxVersion"),

    OpenSourceLibrary("org.postgresql:postgresql:$postgreVersion"),

    OpenSourceLibrary("com.zaxxer:HikariCP:$hikariVersion"),

    OpenSourceLibrary("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"),
    OpenSourceLibrary("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutinesVersion"),
    OpenSourceLibrary("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutinesVersion"),
    OpenSourceLibrary("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"),
    OpenSourceLibrary("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"),
    OpenSourceLibrary("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion"),

    OpenSourceLibrary("javax.inject:javax.inject:1"),
    OpenSourceLibrary("javax.ws.rs:jsr311-api:1.1.1"),

    OpenSourceLibrary("com.github.blindpirate:annotation-magic:$annotationMagicVersion"),

    OpenSourceLibrary("org.slf4j:slf4j-api:$slf4jVersion"),

    OpenSourceLibrary(
        "jackson-core", "com.fasterxml.jackson.core:jackson-core", jacksonVersion,
        "Jackson", "https://github.com/FasterXML/jackson-core", Apache2
    ),
    OpenSourceLibrary("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion"),
    OpenSourceLibrary("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion"),
    OpenSourceLibrary("com.fasterxml.jackson:jackson-bom:$jacksonVersion"),
    OpenSourceLibrary("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion"),
    OpenSourceLibrary("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion"),
    OpenSourceLibrary("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:$jacksonVersion"),
    OpenSourceLibrary("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion"),
    OpenSourceLibrary("com.fasterxml.jackson.module:jackson-module-parameter-names:$jacksonVersion"),
    OpenSourceLibrary("commons-codec:commons-codec:$apacheCommonsCodecVersion"),

    OpenSourceLibrary(
        "commons-lang3", "org.apache.commons:commons-lang3", commonsLangVersion,
        "Apache Commons Lang", "https://github.com/apache/commons-lang", Apache2
    ),
    OpenSourceLibrary("commons-io:commons-io:$commonsIOVersion"),
    OpenSourceLibrary("com.google.guava:guava:$guavaVersion"),


    OpenSourceLibrary("org.apiguardian:apiguardian-api:$apiGuadianVersion"),

    OpenSourceLibrary("io.mockk:mockk:$mockKVersion"),

    OpenSourceLibrary("junit:junit:$junit4Version"),
    OpenSourceLibrary("org.junit.jupiter:junit-jupiter-api:$junitVersion"),
    OpenSourceLibrary("org.junit.jupiter:junit-jupiter-engine:$junitVersion"),
    OpenSourceLibrary("org.junit.vintage:junit-vintage-engine:$junitVersion"),
    OpenSourceLibrary("org.junit.jupiter:junit-jupiter-params:$junitVersion"),
    OpenSourceLibrary("io.github.glytching:junit-extensions:$junitExtensionsVersion"),

    OpenSourceLibrary("org.gebish:geb-core:$gebVersion"),
    OpenSourceLibrary("org.seleniumhq.selenium:selenium-firefox-driver:${seleniumVersion}"),
    OpenSourceLibrary("org.seleniumhq.selenium:selenium-api:${seleniumVersion}"),
    OpenSourceLibrary("org.seleniumhq.selenium:selenium-chrome-driver:${seleniumVersion}"),
    OpenSourceLibrary("org.seleniumhq.selenium:selenium-remote-driver:${seleniumVersion}"),
    OpenSourceLibrary("org.seleniumhq.selenium:selenium-support:${seleniumVersion}"),
    OpenSourceLibrary("com.google.code.findbugs:annotations:$findBugsAnnotationVersion"),

    OpenSourceLibrary("org.mockito:mockito-core:$mockitoVersion"),
    OpenSourceLibrary("org.mockito:mockito-junit-jupiter:$mockitoVersion"),

    OpenSourceLibrary("org.slf4j:jcl-over-slf4j:$jclOverSlf4JVersion"),
    OpenSourceLibrary("ch.qos.logback:logback-classic:$logbackVersion"),
    OpenSourceLibrary("ch.qos.logback:logback-core:$logbackVersion"),
    OpenSourceLibrary("com.github.blindpirate:cloudwatch-logback-appender:$logbackCloudWatchAppenderVersion"),

    OpenSourceLibrary("com.auth0:java-jwt:$javaJwtVersion"),
    OpenSourceLibrary("org.bouncycastle:bcprov-jdk15on:$bouncycastleVersion"),

    // This is for cross region replication and Kinesis, which doesn't support V2
    OpenSourceLibrary("com.amazonaws:aws-java-sdk:$awsSdkV1Version"),
    OpenSourceLibrary("software.amazon.awssdk:s3:$awsSdkV2Version"),
    OpenSourceLibrary("software.amazon.awssdk:dynamodb:$awsSdkV2Version"),
    OpenSourceLibrary("software.amazon.awssdk:dynamodb-enhanced:$awsSdkV2Version"),
    OpenSourceLibrary("software.amazon.awssdk:netty-nio-client:$awsSdkV2Version"),
    OpenSourceLibrary("com.github.blindpirate:dynamodb-cross-region-replication:$dynamodbCrossRegionReplicationVersion"),

    OpenSourceLibrary("org.commonmark:commonmark-ext-task-list-items:$commonmarkVersion"),

    OpenSourceLibrary("com.github.houbb:opencc4j:$opencc4jVersion"),

    OpenSourceLibrary("com.github.ben-manes.caffeine:caffeine:$caffeineVersion"),
    OpenSourceLibrary("com.github.ben-manes.caffeine:guava:$caffeineVersion"),

    OpenSourceLibrary("org.testcontainers:testcontainers:$testContainersVersion"),
    OpenSourceLibrary("org.testcontainers:selenium:$testContainersVersion"),
    OpenSourceLibrary("org.testcontainers:junit-jupiter:$testContainersVersion"),
    OpenSourceLibrary("org.kodein.di:kodein-di:$kodeinVersion"),

    OpenSourceLibrary("GSAP", "https://greensock.com/", GreenSockStandardLicense),
    OpenSourceLibrary("fireworks-canvas", "https://github.com/tswaters/fireworks", WTFPL),

    OpenSourceLibrary("org.jetbrains.kotlin-wrappers:kotlin-react:$kotlinReactVersion"),
    OpenSourceLibrary("org.jetbrains.kotlin-wrappers:kotlin-react-dom:$kotlinReactVersion"),
    OpenSourceLibrary("org.jetbrains.kotlin-wrappers:kotlin-extensions:1.0.1-pre.382"),
    OpenSourceLibrary("kotlin-react", "https://github.com/JetBrains/kotlin-wrappers", Apache2),
    OpenSourceLibrary(name = "react", version = reactVersion, creditName = "React", url = "https://reactjs.org", license = MIT),
    OpenSourceLibrary(name = "react-bootstrap", version = reactBootstrapVersion),
    OpenSourceLibrary(creditName = "Bootstrap", url = "https://getbootstrap.com", license = MIT),
    OpenSourceLibrary(name = "@material-ui/core", version = materialUIVersion),
    OpenSourceLibrary("org.jetbrains.kotlinx:kotlinx-browser:$kotlinBrowserVersion"),
    OpenSourceLibrary(
        name = "react-transition-group",
        creditName = "react-transition-group",
        url = "https://github.com/reactjs/react-transition-group",
        version = reactTransitionGroupVersion,
        license = Apache2
    ),
    OpenSourceLibrary(name = "in-browser-download", creditName = "in-browser-download", url = "https://github.com/kchapelier/in-browser-download", version = inBrowserDownloadVersion, license = MIT),
    OpenSourceLibrary(name = "get-image-pixels", creditName = "get-image-pixels", url = "https://github.com/mattdesl/get-image-pixels", version = getImagePixelsVersion, license = MIT),
    OpenSourceLibrary(name = "react-icons", creditName = "react-icons", url = "https://github.com/react-icons/react-icons", version = reactIconsVersion, license = MIT),
    OpenSourceLibrary(
        name = "bootstrap-switch-button-react",
        creditName = "bootstrap-switch-button-react",
        url = "https://github.com/gitbrent/bootstrap-switch-button-react",
        version = bootstrapSwitchButtonReactVersion,
        license = MIT
    ),
    OpenSourceLibrary("org.litote.kmongo:kmongo-coroutine-native:$kmongoVersion"),
    OpenSourceLibrary("org.mongodb:bson:$mongodbJavaDriverVersion"),
    OpenSourceLibrary("com.google.cloud:libraries-bom:$googleTranslationSdkVersion"),
    OpenSourceLibrary(
        name = "batik-svggen",
        groupArtifactId = "org.apache.xmlgraphics:batik-svggen",
        version = batikVersion,
        creditName = "Apache Batik Svggen",
        url = "https://github.com/apache/xmlgraphics-batik",
        license = Apache2
    ),
    OpenSourceLibrary(
        name = "github-api",
        groupArtifactId = "org.kohsuke:github-api",
        version = hub4jGitHubApiVersion,
        creditName = "GitHub API (hub4j)",
        url = "https://github.com/hub4j/github-api",
        license = MIT
    ),
    OpenSourceLibrary("org.apache.xmlgraphics:batik-dom:$batikVersion"),
    OpenSourceLibrary("org.apache.xmlgraphics:batik-swing:$batikVersion"),
    OpenSourceLibrary(
        name = "react-player",
        creditName = "react-player",
        url = "https://github.com/CookPete/react-player",
        version = reactPlayerVersion,
        license = MIT
    ),
    OpenSourceLibrary(
        name = "react-select",
        creditName = "react-select",
        url = "https://github.com/JedWatson/react-select",
        version = reactSelectVersion,
        license = MIT
    ),
    OpenSourceLibrary(
        name = "commonmark",
        groupArtifactId = "org.commonmark:commonmark",
        version = commonmarkVersion,
        creditName = "commonmark-java",
        url = "https://github.com/commonmark/commonmark-java",
        license = BSD2Clause
    ),
    OpenSourceLibrary(
        name = "java-otp",
        groupArtifactId = "com.eatthepath:java-otp",
        version = javaOptVersion,
        creditName = "java-otp",
        url = "https://github.com/jchambers/java-otp",
        license = MIT
    ),
    OpenSourceLibrary(
        name = "react-textarea-autosize",
        version = reactTextAreaAutosizeVersion,
        creditName = "react-textarea-autosize",
        url = "https://github.com/Andarist/react-textarea-autosize",
        license = MIT
    ),
    OpenSourceLibrary(
        name = "canvas-confetti",
        version = canvasConfettiVersion,
        creditName = "canvas-confetti",
        url = "https://github.com/catdad/canvas-confetti",
        license = ISC
    ),
    OpenSourceLibrary(
        name = "echarts",
        version = "",
        creditName = "Apache ECharts",
        url = "https://echarts.apache.org/",
        license = Apache2
    ),
    OpenSourceLibrary(
        name = "prism",
        version = "",
        creditName = "Prism",
        url = "https://prismjs.com/",
        license = MIT
    ),
    OpenSourceLibrary(
        name = "spring-core",
        groupArtifactId = "org.springframework:spring-core",
        version = springCoreVersion,
        creditName = "spring-core",
        url = "https://github.com/spring-projects/spring-framework",
        license = Apache2
    ),
    OpenSourceLibrary(
        name = "githubdiffparser",
        groupArtifactId = "com.github.stkent:githubdiffparser",
        version = "1.0.1", // we maintain a fork, this is just for displaying
        creditName = "githubdiffparser",
        url = "https://github.com/stkent/githubdiffparser",
        license = Apache2
    ),
    OpenSourceLibrary(
        name = "java-diff-utils",
        groupArtifactId = "io.github.java-diff-utils:java-diff-utils",
        version = javaDiffUtilsVersion,
        creditName = "java-diff-utils",
        url = "https://github.com/java-diff-utils/java-diff-utils",
        license = Apache2
    ),
    OpenSourceLibrary(
        name = "diffparser",
        groupArtifactId = "io.reflectoring.diffparser:diffparser",
        version = unifiedDiffParserVersion,
        creditName = "diffparser",
        url = "https://github.com/thombergs/diffparser",
        license = Apache2
    ),
    OpenSourceLibrary(
        name = "jgit",
        groupArtifactId = "org.eclipse.jgit:org.eclipse.jgit",
        version = jgitVersion,
        creditName = "jgit",
        url = "https://www.eclipse.org/jgit/",
        license = EDL
    ),
    OpenSourceLibrary(
        name = "checkstyle",
        groupArtifactId = "com.puppycrawl.tools:checkstyle",
        version = checkstyleVersion,
        creditName = "checkstyle",
        url = "https://checkstyle.sourceforge.io/",
        license = LGPL
    ),
    OpenSourceLibrary(
        name = "react-markdown",
        version = reactMarkdownVersion,
        creditName = "react-markdown",
        url = "https://github.com/remarkjs/react-markdown",
        license = MIT
    ),
    OpenSourceLibrary(
        name = "rehype-raw",
        version = rehypeRawVersion,
        creditName = "rehype-raw",
        url = "https://github.com/rehypejs/rehype-raw",
        license = MIT
    ),
    OpenSourceLibrary(
        name = "rehype-external-links",
        version = rehypeExternalLinksVersion,
        creditName = "rehype-external-links",
        url = "https://github.com/rehypejs/rehype-external-links",
        license = MIT
    ),
    OpenSourceLibrary(
        name = "remark-gfm",
        version = remarkGfmVersion,
        creditName = "remark-gfm",
        url = "https://github.com/remarkjs/remark-gfm",
        license = MIT
    ),
    OpenSourceLibrary(
        name = "animated-gif-lib-for-java",
        groupArtifactId = "com.madgag:animated-gif-lib",
        version = animatedGifLibVersion,
        creditName = "animated-gif-lib-for-java",
        url = "https://github.com/rtyley/animated-gif-lib-for-java",
        license = Free
    ),
)

val nameToLib = mutableMapOf<String, OpenSourceLibrary>()

dependencies.forEach {
    if (nameToLib.contains(it.name)) {
        // artifactId conflict, remove
        val otherLib = nameToLib.remove(it.name)!!
        nameToLib[it.groupArtifactId] = it
        nameToLib[otherLib.groupArtifactId] = otherLib
    } else {
        nameToLib[it.name] = it
    }

    nameToLib[it.groupArtifactId] = it
}

fun getGavByName(name: String): String {
    return nameToLib.getValue(name).gav
}

fun getVersionByName(name: String): String {
    return nameToLib.getValue(name).version
}

rootProject.extensions.configure<ExtraPropertiesExtension>("ext") {
    set("libs", ::getGavByName)
    set("libVersions", ::getVersionByName)
    set("oss", dependencies.filter { it.creditName.isNotEmpty() }.sortedBy { it.creditName.toLowerCase() })
}
