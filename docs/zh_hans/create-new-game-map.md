# 创建新的游戏地图

我们使用[`Tiled`](https://www.mapeditor.org/)维护游戏的地图。

要创建新的游戏地图，您需要：

1. 运行`./gradlew utils:createNewMap -DmapId=<mapId> -DmapGridWidth=<mapWidth> -DmapGridHeight=<mapHeight>`
2. 编辑`game-data/hierarchy.yml`，添加新地图。
3. 编辑`settings.gradle.kts`，include新的`client/game-<mapId>`子项目.
4. 在`game-data/i18n-common.yml`中加入：

    ```yaml
    - id: «YourNewMapId»
      data:
        EN: «The display name of that map»
    ```

    注意您必须至少提供英文版的文本。

5. 点击IDEA的Gradle window里的"Reload All Gradle Projects"。

现在您应该能够：

- 在本机上启动游戏并且在地图选择下拉菜单中看到新的地图。
- 在`Tiled`编辑生成的地图文件`resources/raw/maps/«mapId»/«mapId».json`。请先阅读[游戏地图贡献者指南](https://github.com/ByteLegend/ByteLegend/blob/master/docs/zh_hans/game-map-contributor-guide.md)。
- 编辑`client/game-«YourNewMap»`中的游戏脚本。

## TBD
