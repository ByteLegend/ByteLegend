# Conventions regarding maps

```text
rootProject/tiled/maps
  |
  |---- JavaIsland
  |      |---- JavaIsland.json
  |      |---- JavaIslandNewbieVillagePub.json
  |      |---- JavaIslandDungeon.json
  |
  |---- GitIsland
  |      |---- GitIsland.json
```

This means there are two "main" island: `JavaIsland` and `GitIsland`, on which there might be sub maps.

This convention is used by:

## Resource processing in build

There's one frontend subproject for each map(submap). For example, in the example above, there will be:

```text
rootProject/client/
   |----- game-JavaIsland
   |----- game-JavaIslandNewbieVillagePub
   |----- game-GitIsland
```

## Backend server

The hierarchy

Backend server uses this information to render a tree-like dropdown box in the game.
