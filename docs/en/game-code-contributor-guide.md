# Game Code Contributor Guide

## Open and Import the Project

Install [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) (Ultimate version or Community version, it doesn't matter) and [Java 11](https://adoptopenjdk.net/),
then `File` - `Open` - select `build.gradle.kts` in the root project - `Open as Project`.

Set project JDK to JDK 11 in `File` - `Project Structure` - `Project Settings` - `Project`.

To start local development server, click `bootRun` in Gradle tool window at the right-top corner.

![Run local development server](https://raw.githubusercontent.com/ByteLegend/ByteLegend/master/docs/images/run-local-development-server.png)

Everytime you make changes to the project, stop the running server and rerun this task to reload the development server.

(Alternatively, you can set `JAVA_HOME` to JDK 11 and run `./gradlew server-opensource:bootRun` command line to start the local server).

## How the Game Works

The game is made of [Kotlin Multiplatform](https://kotlinlang.org/docs/mpp-intro.html), including tech stacks:

- [`React`](https://github.com/JetBrains/kotlin-wrappers) for frontend.
- [Spring Boot](https://spring.io/projects/spring-boot) for opensource server.

## Entry Point: `GamePage`

The game is designed as a modular system. Each frontend UI element is a React component.
Check out [`GamePage`](https://github.com/ByteLegend/ByteLegend/blob/667ac7380ef3dbf4c8df5ae476171b1699fcd3a0/client/game-page/src/main/kotlin/com/bytelegend/client/app/page/GamePage.kt)
for all components we have in the game. You can navigate along the components in `GamePage.render()` to every single component and make changes. Have fun!

## TBD
