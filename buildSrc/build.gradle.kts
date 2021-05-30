plugins {
    `kotlin-dsl`
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    val jacksonVersion = "2.12.2"
    implementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.github.blindpirate:jsonschema2pojo-core:1.1.0.4")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")

    implementation(gradleApi())
}

gradlePlugin {
    plugins {
        register("json2Java") {
            id = "json2Java"
            implementationClass = "com.bytelegend.buildsrc.json2java.Json2JavaPlugin"
        }
    }

    plugins {
        register("buildGameResources") {
            id = "buildGameResources"
            implementationClass = "com.bytelegend.buildsupport.BuildGameResourcesPlugin"
        }
    }
}
