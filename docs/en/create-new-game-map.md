# Create a new game map

We use [`Tiled`](https://www.mapeditor.org/) to maintain game maps.

To create a new game map, you need to:

1. Run `./gradlew utils:createNewMap -DmapId=<mapId> -DmapGridWidth=<mapWidth> -DmapGridHeight=<mapHeight>`
2. Edit `game-data/hierarchy.yml` and add the new map into it.
3. Edit `settings.gradle.kts` to include the new subproject `client/game-<mapId>`.
4. Add an entry in `game-data/i18n-common.yml` in the following format:

    ```yaml
    - id: «YourNewMapId»
      data:
        EN: «The display name of that map»
    ```

    Note that you must provide at least English version of the text.

5. Click "Reload All Gradle Projects" button in Gradle window of IDEA.

Now you should be able to:

- Start the game locally to see the new map in map selection dropdown.
- Edit the generated map `resources/raw/maps/«mapId»/«mapId».json` in `Tiled`(Please read [Game Map Contributor Guide](https://github.com/ByteLegend/ByteLegend/blob/master/docs/en/game-map-contributor-guide.md) first).
- Edit game scripts in `client/game-«YourNewMap»`.

## TBD
