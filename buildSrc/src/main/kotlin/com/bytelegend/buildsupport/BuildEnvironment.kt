package com.bytelegend.buildsupport

import org.gradle.api.Project

fun Project.getEnvironment() = findProperty("environment") ?: "dev"

fun Project.isDebug() = findProperty("debug") !== null || findProperty("debug")?.toString() == "true"
