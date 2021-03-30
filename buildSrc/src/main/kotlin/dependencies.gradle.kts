import com.bytelegend.buildsupport.OpenSourceLibrary
import com.bytelegend.buildsupport.OpenSourceLicense.Apache2
import com.bytelegend.buildsupport.OpenSourceLicense.GreenSockStanardLicense
import com.bytelegend.buildsupport.OpenSourceLicense.MIT
import com.bytelegend.buildsupport.OpenSourceLicense.WTFPL

val forestVersion = "0.3.5-SNAPSHOT"
val vertxVersion = "4.0.3"
val guiceVersion = "4.2.3-SNAPSHOT"
val guavaVersion = "28.2-jre"
val jacksonVersion = "2.12.1"
val kotlinVersion = "1.4.31"
val kotlinCoroutinesCommonVersion = "1.3.8"
val kotlinxVersion = "1.4.3"
val junitVersion = "5.7.0"
val junit4Version = "4.13"
val mockKVersion = "1.11.0"
val apiGuadianVersion = "1.1.0"
val log4jVersion = "2.13.3"
val logbackVersion = "1.2.3"
val slf4jVersion = "1.7.30"
val commonsLangVersion = "3.10"
val junitExtensionsVersion = "2.4.0"
val commonsIOVersion = "2.7"
val annotationMagicVersion = "0.2.5"
val jsr311ApiVersion = "1.1.1"
val gebVersion = "3.4"
val seleniumVersion = "3.141.59"
val groovyVersion = "2.5.14"
val findBugsAnnotationVersion = "3.0.1"
val httpclient = "4.5.12"
val mockitoVersion = "3.5.10"
val apacheCommonsCodecVersion = "1.10"
val postgreVersion = "42.2.18"
val hikariVersion = "3.4.5"
val javaJwtVersion = "3.11.0"
val bouncycastleVersion = "1.59"
val awsSdkV1Version = "1.11.978"
val awsSdkV2Version = "2.16.22"
val opencc4jVersion = "1.6.0"
val kotlinxSerializationVersion = "1.0.1"
val dynamodbCrossRegionReplicationVersion = "1.2.1.16"
val caffeineVersion = "2.8.8"
val testContainersVersion = "1.15.1"
val kodeinVersion = "7.2.0"
val kotlinReactVersion = "17.0.1-pre.148-kotlin-1.4.30"
val reactVersion = "17.0.1"
val kotlinStyledVersion = "5.2.1-pre.148-kotlin-1.4.30"
val styledComponentsVersion = "5.2.1"
val reactBootstrapVersion = "1.4.3"
val materialUIVersion = "4.11.2"
val kotlinBrowserVersion = "0.2"
val reactTransitionGroupVersion = "4.4.1"
val inBrowserDownloadVersion = "2.0.0"
val getImagePixelsVersion = "1.0.1"
val reactIconsVersion = "3.11.0"
val bootstrapSwitchButtonReactVersion = "1.2.0"
val kmongoVersion = "4.2.4"
val mongodbJavaDriverVersion = "4.2.0"

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

    OpenSourceLibrary("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxVersion"),
    OpenSourceLibrary("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$kotlinCoroutinesCommonVersion"),
    OpenSourceLibrary("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$kotlinxVersion"),
    OpenSourceLibrary("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$kotlinxVersion"),
    OpenSourceLibrary("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"),
    OpenSourceLibrary("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion"),

    OpenSourceLibrary("javax.inject:javax.inject:1"),
    OpenSourceLibrary("io.forestframework:guice:$guiceVersion"),
    OpenSourceLibrary("javax.ws.rs:jsr311-api:1.1.1"),

    OpenSourceLibrary("com.github.blindpirate:annotation-magic:$annotationMagicVersion"),

    OpenSourceLibrary("org.slf4j:slf4j-api:$slf4jVersion"),

    OpenSourceLibrary(
        "jackson-core", "com.fasterxml.jackson.core:jackson-core", jacksonVersion,
        "Jackson", "https://github.com/FasterXML/jackson-core", Apache2
    ),
    OpenSourceLibrary("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion"),
    OpenSourceLibrary("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion"),
    OpenSourceLibrary("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion"),
    OpenSourceLibrary("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion"),
    OpenSourceLibrary("commons-codec:commons-codec:$apacheCommonsCodecVersion"),

    OpenSourceLibrary(
        "commons-lang3", "org.apache.commons:commons-lang3", commonsLangVersion,
        "Apache Commons Lang", "https://github.com/apache/commons-lang", Apache2
    ),
    OpenSourceLibrary("commons-io:commons-io:$commonsIOVersion"),
    OpenSourceLibrary("com.google.guava:guava:$guavaVersion"),

    OpenSourceLibrary("org.apache.logging.log4j:log4j-slf4j-impl:$log4jVersion"),
    OpenSourceLibrary("org.apache.logging.log4j:log4j-core:$log4jVersion"),

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
    OpenSourceLibrary("org.codehaus.groovy:groovy-all:$groovyVersion"),
    OpenSourceLibrary("com.google.code.findbugs:annotations:$findBugsAnnotationVersion"),

    OpenSourceLibrary("org.apache.httpcomponents:httpclient:$httpclient"),
    OpenSourceLibrary("org.mockito:mockito-core:$mockitoVersion"),
    OpenSourceLibrary("org.mockito:mockito-junit-jupiter:$mockitoVersion"),

    OpenSourceLibrary("ch.qos.logback:logback-classic:$logbackVersion"),
    OpenSourceLibrary("ch.qos.logback:logback-core:$logbackVersion"),

    OpenSourceLibrary("com.auth0:java-jwt:$javaJwtVersion"),
    OpenSourceLibrary("org.bouncycastle:bcprov-jdk15on:$bouncycastleVersion"),

    // This is for cross region replication and Kinesis, which doesn't support V2
    OpenSourceLibrary("com.amazonaws:aws-java-sdk:$awsSdkV1Version"),
    OpenSourceLibrary("software.amazon.awssdk:dynamodb:$awsSdkV2Version"),
    OpenSourceLibrary("software.amazon.awssdk:dynamodb-enhanced:$awsSdkV2Version"),
    OpenSourceLibrary("software.amazon.awssdk:netty-nio-client:$awsSdkV2Version"),
    OpenSourceLibrary("com.github.blindpirate:dynamodb-cross-region-replication:$dynamodbCrossRegionReplicationVersion"),

    OpenSourceLibrary("com.github.houbb:opencc4j:$opencc4jVersion"),

    OpenSourceLibrary("com.github.ben-manes.caffeine:caffeine:$caffeineVersion"),
    OpenSourceLibrary("com.github.ben-manes.caffeine:guava:$caffeineVersion"),

    OpenSourceLibrary("org.testcontainers:testcontainers:$testContainersVersion"),
    OpenSourceLibrary("org.testcontainers:selenium:$testContainersVersion"),
    OpenSourceLibrary("org.testcontainers:junit-jupiter:$testContainersVersion"),
    OpenSourceLibrary("org.kodein.di:kodein-di:$kodeinVersion"),

    OpenSourceLibrary("GSAP", "https://greensock.com/", GreenSockStanardLicense),
    OpenSourceLibrary("fireworks-canvas", "https://github.com/tswaters/fireworks", WTFPL),

    OpenSourceLibrary("org.jetbrains:kotlin-react:$kotlinReactVersion"),
    OpenSourceLibrary("org.jetbrains:kotlin-react-dom:$kotlinReactVersion"),
    OpenSourceLibrary("kotlin-react", "https://github.com/JetBrains/kotlin-wrappers", Apache2),
    OpenSourceLibrary(name = "react", version = reactVersion, creditName = "React", url = "https://reactjs.org", license = MIT),
    OpenSourceLibrary("org.jetbrains:kotlin-styled:$kotlinStyledVersion"),
    OpenSourceLibrary(name = "styled-components", version = styledComponentsVersion),
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
    OpenSourceLibrary("org.mongodb:bson:$mongodbJavaDriverVersion")
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
