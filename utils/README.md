# ByteLegend map conventions

- Tiles are placed in layers. All visible tile layers will be displayed on the game map as they are, with the following exceptions:
  - Layer `Blockers` are used to place blocker signs. They are not displayed on the game map.
  - Layer `Player` is a special marker layer. Layers above this layer are displayed above the players in the game,
    layers below this layer are displayed under the players in the map.
  - Layers in `DynamicSprites` are not directly displayed. They're usually dynamically added by game scripts.
    - Each such layer should only contain a single sprite. The layer name is used as `GameMapDynamicSprite.id`.
- Object layers are used to store `GameMapObject`s. See class `TiledObjectType`.
