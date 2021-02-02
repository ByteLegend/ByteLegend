plugins {
    `kotlin-dsl`
    id("java-library")
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core:2.12.1")
    implementation("org.jsonschema2pojo:jsonschema2pojo-core:1.0.2")
    implementation("org.postgresql:postgresql:42.2.18")

    implementation(gradleApi())
}

gradlePlugin {
    plugins {
        register("json2Java") {
            id = "json2Java"
            implementationClass = "com.bytelegend.buildsrc.json2java.Json2JavaPlugin"
        }
    }
}
