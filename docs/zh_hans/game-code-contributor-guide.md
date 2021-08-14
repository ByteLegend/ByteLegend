# 游戏代码贡献者指南

## 打开并导入项目

安装[IntelliJ IDEA](https://www.jetbrains.com/idea/download/) (Ultimate/Community版均可)和[Java 11](https://adoptopenjdk.net/)，
然后`File` - `Open` - 选择根目录的`build.gradle.kts` - `Open as Project`。

设置项目JDK为JDK 11：`File` - `Project Structure` - `Project Settings` - `Project`。

欲启动本地开发服务器，点击右上角Gradle tool window中的`bootRun`。

![Run local development server](https://raw.githubusercontent.com/ByteLegend/ByteLegend/master/docs/images/run-local-development-server.png)

每次对项目作出修改之后，您需要停止当前运行的服务器，再重启以加载更新后的文件。

（另外您也可以设置`JAVA_HOME`指向JDK 11然后在命令行里运行`./gradlew server-opensource:bootRun`来启动本地服务器）。

## 游戏是如何工作的

游戏主要由[Kotlin Multiplatform](https://kotlinlang.org/docs/mpp-intro.html)编写，技术栈有：

- 前端[`React`](https://github.com/JetBrains/kotlin-wrappers).
- 开源版本的后端[Spring Boot](https://spring.io/projects/spring-boot).

## 游戏的主入口: `GamePage`

游戏是高度模块化的，每个前端的UI元素都是一个React组件。请看[`GamePage`](https://github.com/ByteLegend/ByteLegend/blob/667ac7380ef3dbf4c8df5ae476171b1699fcd3a0/client/game-page/src/main/kotlin/com/bytelegend/client/app/page/GamePage.kt)
以了解游戏中的组件。您可以顺着`GamePage.render()`中的组件列表跳转到组件的代码。

## TBD
