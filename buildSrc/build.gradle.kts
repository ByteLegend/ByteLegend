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
plugins {
    `kotlin-dsl`
    `java-library`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    val jacksonVersion = "2.12.3"
    implementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("com.github.blindpirate:jsonschema2pojo-core:1.1.0.4")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")

    implementation("gradle.plugin.com.github.jengelman.gradle.plugins:shadow:7.0.0")

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

    plugins {
        register("setupBuildVersion") {
            id = "setupBuildVersion"
            implementationClass = "com.bytelegend.buildsupport.SetupBuildVersionPlugin"
        }
    }

    plugins {
        register("deployment") {
            id = "deployment"
            implementationClass = "com.bytelegend.buildsupport.DeploymentPlugin"
        }
    }

    plugins {
        register("shadow-release") {
            id = "shadow-release"
            implementationClass = "com.bytelegend.buildsupport.ShadowReleasePlugin"
        }
    }
}
