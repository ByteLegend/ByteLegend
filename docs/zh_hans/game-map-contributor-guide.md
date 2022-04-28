# 游戏地图贡献者指南

要编辑游戏地图，您需要安装[Tiled](https://www.mapeditor.org/)。

- 每个地图都是一个JSON文件：`resources/raw/maps/«mapId»/«mapId».json`。
- Tileset图片在`resources/raw/tilesets/`。
- Tileset定义文件在`resources/raw/tileset-jsons`。

点击`File` - `Open File or Project` - 选择地图JSON文件以编辑地图。

我们尽一切可能让地图编辑成为"所见即所得"模式，也就是说，你在`Tiled`中看到就是你在游戏中看到的。图层显示顺序和游戏中一致。

要修改地图，在`Tiled`中编辑tile layer，保存并重启本地开发服务器即可。修改会自动被加载并构建。

## 游戏地图约定

除了tile之外，地图还有一些额外的信息和约定：

- `Blockers`图层标记了地图中的不可到达点。此图层在游戏中不可见。
- `Player`是一个特殊的图层，标记了玩家所在的图层。此层之上的图层在游戏里也会被绘制在玩家之上。
  您可以利用这个特性达到特殊的效果（如显示在玩家之上的树冠）。
  不要在这个图层上绘制任何东西，它们会被忽略。
- `DynamicSprites` group layer。
  - `DynamicSprites`是一个包含dynamic sprite的group layer。每个dynamic sprite都必须位于单独的图层。
      图层的名字是`DynamicSprite`的id，可以直接在游戏脚本中被引用，参考`class DynamicSprite`。
  - 所有的dynamic sprite都不是直接画在游戏里的，而是由游戏脚本控制的。如，游戏脚本可以读取玩家的状态并绘制不同的帧：如果玩家完成了挑战，
      绘制打开的宝箱，否则绘制关闭的宝箱。

## Game Objects

除了tile，在游戏地图里你可以加入多种类型的对象，它们可以被游戏脚本直接引用。例如，你可以在地图上加入一个point对象然后在该点放一个NPC。
我们支持下列类型的Game Object（详见`enum class GameMapObjectType`）：

- `GameMapText`: 在地图上浮动的一段文字，如海上的"去往Git岛"字样。注意对象的`text`属性会被忽略，`name`属性用于在国际化文本中
  搜索对应的文本，所以不要忘记在`game-data/<mapId>/i18n.yml`中加入对应的国际化文本。
  在`Tiled`中它的`type`属性必须被设置为`GameMapText`。
- `GameMapRegion`: 一个区域，用于绘制小地图和路线图。它必须是一个`Tiled`中的多边形，且`type`属性设置为`GameMapRegion`。
- `GameMapPoint`: 地图中的一个点，它必须是一个`Tiled`点对象，且`type`属性设置为`GameMapPoint`。
- `GameMapCurve`: 在地图中绘制的一段曲线（如通往其他岛的航线）。您需要在`Tiled`中放置若干个点，使得：
  - 每个点的`type`属性设置为`GameMapCurvePoint`。
  - 每个点都必须有一个`order`属性，指明该点在曲线中的序号。
