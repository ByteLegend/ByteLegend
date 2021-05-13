package com.bytelegend.app.client.api

import com.bytelegend.app.client.api.dsl.ObjectsBuilder
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.GameMap
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.GridSize
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole

val HERO_ID = "hero"

interface GameObjectContainer {
    fun <T : GameObject> getByIdOrNull(id: String): T?
    fun <T : GameObject> getById(id: String): T
    fun add(gameObject: GameObject)
    fun <T : GameObject> remove(id: String): T?
    fun getByCoordinate(coordinate: GridCoordinate): List<GameObject>
    fun <T : GameObject> getByRole(role: GameObjectRole): List<T>
}

enum class LogLevel {
    Debug,
    Info,
    Warn,
    Error
}

interface Logger {
    val logLevel: LogLevel
    val debugEnabled: Boolean
    val infoEnabled: Boolean
    val warnEnabled: Boolean
    val errorEnabled: Boolean
    fun debug(message: String)
    fun info(message: String)
    fun warn(message: String)
    fun error(message: String)
}

interface GameRuntime {
    val hero: Character?
    val heroPlayer: Player
    val RRBD: String
    val locale: Locale
    val eventBus: EventBus
    val sceneContainer: GameSceneContainer
    val currentTimeMillis: Long
    val activeScene: GameScene
    val modalController: ModalController
    val bannerController: BannerController
    val toastController: ToastController

    fun i(textId: String, vararg args: String): String
}

interface GameContainerSizeAware {

    /**
     * Represent the whole "game container" size. The "game container" is usually
     * the whole browser window, but in certain cases it might be a div or so.
     *
     * Note that it's resizeable. The implementation class should properly respond to
     * the resizing operation, like adjust the inside canvas.
     */
    var gameContainerSize: PixelSize
}

/*
When the game map is far bigger than canvas (model 1):
-----------------------------------------------------------------------------------------------
|    The whole game map                                                                       |
|                                                                                             |
|      ----------------------------------------------------------------------------------     |
|      |   The game container (browser window at most of the time)                       |    |
|      |                                                                                 |    |
|      |                                                                                 |    |
|      |      -----------------------------------------------------------------------    |    |
|      |      |  The map canvas/UI container                                         |   |    |
|      |      |                                                                      |   |    |
|      |      |                                                                      |   |    |
|      |      |                                                                      |   |    |
|      |      |                                                                      |   |    |
|      |      |                                                                      |   |    |
|      |      |                                                                      |   |    |
|      |      |                                                                      |   |    |
|      |      -----------------------------------------------------------------------    |    |
|      |                                                                                 |    |
|      -----------------------------------------------------------------------------------    |
|                                                                                             |
|---------------------------------------------------------------------------------------------|

When the game map is not far bigger than canvas (model 2):
-----------------------------------------------------------------------------------------------
|      The game container (browser window at most of the time)                                |
|                                                                                             |
|      ----------------------------------------------------------------------------------     |
|      |   The UI container (for displaying UI elements, i.e. dropdowns, widgets)        |    |
|      |                                                                                 |    |
|      |                                                                                 |    |
|      |      -----------------------------------------------------------------------    |    |
|      |      |  The map canvas/game map                                             |   |    |
|      |      |                                                                      |   |    |
|      |      |                                                                      |   |    |
|      |      |                                                                      |   |    |
|      |      |                                                                      |   |    |
|      |      |                                                                      |   |    |
|      |      |                                                                      |   |    |
|      |      |                                                                      |   |    |
|      |      -----------------------------------------------------------------------    |    |
|      |                                                                                 |    |
|      -----------------------------------------------------------------------------------    |
|                                                                                             |
|---------------------------------------------------------------------------------------------|

How we calculate the container size:

1. Get the game container size and map size.
2. Shrink the game container by ~100 px in all directions to get the "max game canvas size".
3. If game map is bigger than "max game canvas size", use model 1.
4. Otherwise, use model 2.
*/
interface GameCanvasState : GameContainerSizeAware {
    /**
     * The canvas' top-left corner pixel coordinate in the game container, and the pixel size
     */
    fun getCanvasCoordinateInGameContainer(): PixelCoordinate

    /**
     * The UI's top-left corner pixel coordinate in the game container
     */
    fun getUICoordinateInGameContainer(): PixelCoordinate

    /**
     * The canvas' top-left corner pixel coordinate in the whole game map, and the pixel size
     */
    fun getCanvasCoordinateInMap(): PixelCoordinate

    fun getCanvasGridCoordinateInMap(): GridCoordinate

    /**
     * The canvas size in pixel, must be integer multiple of tile size
     */
    fun getCanvasPixelSize(): PixelSize

    fun getUIContainerSize(): PixelSize

    fun getCanvasGridSize(): GridSize

    /**
     * Move canvasCoordinateInMap to map's coordinate, controlled by mini map and hero indicator
     */
    fun moveTo(coordinate: PixelCoordinate)

    /**
     * Does canvas cover the whole map?
     * If true, minimap and canvas border is not required.
     */
    val mapCoveredByCanvas: Boolean
}

interface GameRuntimeAware {
    val gameRuntime: GameRuntime
}

interface GameSceneAware {
    val gameScene: GameScene
    val tileSize: PixelSize
        get() = gameScene.map.tileSize
    val gameRuntime: GameRuntime
        get() = gameScene.gameRuntime
}

interface GameScene : GameContainerSizeAware, GameRuntimeAware {
    val isActive: Boolean

    /**
     * The map of current scene
     */
    val map: GameMap

    /**
     * The tileset of current scene
     */
    val tileset: ImageResourceData

    /**
     * 2d matrix of blockers.
     * 0 means "nothing".
     * >0 means "it's a blocker for everyone"
     * <0 means "it's a blocker for everyone other than hero, like NPC,other players"
     *
     * So it's a bit tricky: when an NPC steps into a tile, this number decrements by 1,
     * not increment.
     */
    val blockers: Array<Array<Int>>
    val objects: GameObjectContainer
    val canvasState: GameCanvasState
    val playerMissions: PlayerMissionContainer

    fun objects(block: ObjectsBuilder.() -> Unit)
    fun scripts(block: ScriptsBuilder.() -> Unit)
}

interface PlayerMissionContainer {
    /**
     * Is the mission accomplished?
     */
    fun missionAccomplished(missionId: String): Boolean

    /**
     * How many star the player gets from the mission?
     */
    fun missionStar(missionId: String): Int
}

interface StateContainer {
    fun hasState(state: String): Boolean
    fun getState(name: String): String
    suspend fun removeState(state: String)
    suspend fun putState(key: String, value: String)
    suspend fun putState(key: String) = putState(key, "1")
}

interface GameSceneContainer : GameContainerSizeAware {
    fun getSceneByIdOrNull(mapId: String): GameScene?

    fun getSceneById(mapId: String): GameScene

    /**
     * The current active scene. When the scene is loading (e.g. the game is initializing or
     * switching scenes), return null.
     */
    val activeScene: GameScene?

    /**
     * Load a scene, the following resources will be loaded before proceeding:
     *
     * 1. RRBD/map/{mapId}/map.json
     * 2. RRBD/map/{mapId}/tileset.png
     * 3. RRBD/js/game-{mapId}.js
     * 4. RRBD/i18n/{mapId}/{locale}.json
     *
     * If {switchAfterLoad} is true, the UI will switch after loading.
     */
    fun loadScene(mapId: String, switchAfterLoad: Boolean = true, onFinish: suspend (GameScene?, GameScene) -> Unit = { _, _ -> })
}

interface ModalController {
    val visible: Boolean
    fun showModal(contentId: String, titleId: String? = null, onClose: UnitFunction = {})
}

interface BannerController {
    fun showWarningBanner(content: String)
}

interface ToastController {
    fun addToast(headerHtml: String, bodyHtml: String, autoHideMs: Int = 0)
}
