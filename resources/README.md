# Resource management

During the build, all resources are processed and copied to runtime resource base directory (aka. RRBD),

## Dev environment

RRBD is `<project-root>/utils/build/game-resources`.

The application requests resources at `http://localhost:8080/static/20201201201314/<resource-path>`.

## Staging/Production environment

RRBD is `<s3-bucket>/<build-timestamp>`, e.g. `<s3-bucket>/20201201201314`.

The application requests resources at `https://cdn.bytelegend.com/20201201201314/<resource-path>`.

## RRBD structure

```text
RRBD
  ├── js/                             
  │   |── game-page.js
  │   |── game-JavaIsland.js
  │   |── game-JavaIslandNewbieVillageBar.js
  |
  ├── lib/       
  │   └── bootstrap
  | 
  ├── css/
  │   └── common.css
  │
  ├── map/
  │   ├── JavaIsland/
  │   │   ├── map.json
  │   │   ├── tileset.png
  │   │
  │   │
  │   └── JavaIslandNewbieVillageBar/
  │
  ├── i18n/
  |   |__ common
  |   │   |__ en.json
  |   |   |__ zh_hans.json
  |   |
  |   |__ JavaIsland
  |       |__ en.json
  |       |__ zh_hans.json
  |
  |
  ├── img/
  │   ├── icon/
  │   ├── animation/
  │   └── player/
  │
  ├── audio/
```

This includes:

- All files under `<project-root>/resources` directories are copied unchanged. In other words, `<project-root>/resources`
  only contains raw resources which don't need to be processed, e.g. audios, js libraries.
- Game maps in `<project-root>/tiled` are linked and processed, then copied to `RRBD/map`.
- `client` modules are compiled by Kotlin/JS and copied to `RRBD/js`, one js file per module.
- I18n resources in `<project-root>/i18n` are processed and copied to `RRBD/i18n`.
