# Game Map Contributor Guide

In order to make changes to the game map, you need to install [Tiled](https://www.mapeditor.org/).

- Each game map is a single JSON file in `resources/raw/maps/«mapId»/«mapId».json`.
- The tileset images are in `resources/raw/tilesets/`.
- The tileset definitions are in `resources/raw/tileset-jsons`.

Click `File` - `Open File or Project` - select the map JSON file to edit the map.
We have been doing our best to make the map editing "What You See Is What You Get", i.e. what you see in `Tiled` is
what we get in the game. The layers are stacked in the same order as in the game.

To make changes, edit the tile layers in `Tiled`, save, then restart the local development server.
Changes will be picked up and rebuilt automatically.

## Game Map Conventions

Apart from the tiles, there are a few conventions to attach extra information to the map.

- `Blockers` layer marks "wall" points in the game map. The marked tiles are not accessible by players in the game. This layer is invisible in the game.
- `Player` is a special layer which marks the layer where players are drawn. All tiles above this layer will be drawn above players in the game.
  You can leverage this mechanism to draw things above players (like canopy hiding players).
  Don't draw anything on this layer, they will be ignored.
- `DynamicSprites` group layer.
  - `DynamicSprites` is a group layer which contains dynamic sprites. Each dynamic sprites must be put into an isolated layer.
      The layer name is the id of `DynamicSprite`, which can be referenced directly in the game script. See `class DynamicSprite`.
  - All dynamic sprites are not directly drawn onto the canvas, but controlled by game scripts. For example, game script can read
      the player's state and draw different animation frame of a dynamic sprite: if the player has already finished the challenge, draw
      an open chest, otherwise draw a closed chest.

## Game Objects

Apart from tiles, in the map, you can attach various kinds of objects, which can be referenced in the game script directly. For example,
you can put a point on the map and place an NPC at the point at the beginning of the game.
The following game object types are supported (See `enum class GameMapObjectType`):

- `GameMapText`: a floating text in the map, for example "To Git Island" on the sea. Note that the `text` property of the object is ignored:
  the `name` property of the object is used to search in i18n texts, so don't forget to put corresponding i18n texts in `game-data/<mapId>/i18n.yml`.
  It must be a `Tiled` text with `type` property set to `GameMapText`.
- `GameMapRegion`: a region used to draw minimap and roadmap. It must be a `Tiled` polygon with `type` property set to `GameMapRegion`.
- `GameMapPoint`: a point in the map. It must be a `Tiled` point with `type` property set to `GameMapPoint`.
- `GameMapCurve`: to draw a curve in map (e.g. sea route to other island), you need to place a few points in `Tiled`:
  - Each point's `type` property set to `GameMapCurvePoint`.
  - Each point must have a `order` property set to the order number value, which represents the order in the curve.
