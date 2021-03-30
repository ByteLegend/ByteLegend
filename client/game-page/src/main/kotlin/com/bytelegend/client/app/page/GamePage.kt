@file:Suppress("DeferredResultUnused")

package com.bytelegend.client.app.page

import com.bytelegend.app.client.api.AudioResource
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.I18nTextResource
import com.bytelegend.app.client.api.ImageResource
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GameInitData
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.playerAnimationSetResourceId
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.engine.Game
import com.bytelegend.client.app.engine.SCENE_LOADING_END_EVENT
import com.bytelegend.client.app.engine.SCENE_LOADING_START_EVENT
import com.bytelegend.client.app.engine.init
import com.bytelegend.client.app.obj.HeroCharacter
import com.bytelegend.client.app.ui.AudioSwitchWidget
import com.bytelegend.client.app.ui.AudioSwitchWidgetProps
import com.bytelegend.client.app.ui.BannerUIComponent
import com.bytelegend.client.app.ui.FpsCounter
import com.bytelegend.client.app.ui.FpsCounterProps
import com.bytelegend.client.app.ui.GameContainer
import com.bytelegend.client.app.ui.GameContainerProps
import com.bytelegend.client.app.ui.GameModal
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameScriptWidgetDisplayLayer
import com.bytelegend.client.app.ui.GameScriptWidgetDisplayLayerProps
import com.bytelegend.client.app.ui.HeroIndicatorWidget
import com.bytelegend.client.app.ui.ICPServerLocationWidget
import com.bytelegend.client.app.ui.LocaleSelectionDropdown
import com.bytelegend.client.app.ui.LocaleSelectionDropdownProps
import com.bytelegend.client.app.ui.MainMapCanvasLayer
import com.bytelegend.client.app.ui.MapCanvasProps
import com.bytelegend.client.app.ui.MapCoordinateTitleWidget
import com.bytelegend.client.app.ui.MapCoordinateWidgetProps
import com.bytelegend.client.app.ui.MapSelectionDropdown
import com.bytelegend.client.app.ui.MapSelectionDropdownProps
import com.bytelegend.client.app.ui.MapTitleWidgets
import com.bytelegend.client.app.ui.MapTitleWidgetsProps
import com.bytelegend.client.app.ui.MiniMapCanvasLayer
import com.bytelegend.client.app.ui.OnlineCounter
import com.bytelegend.client.app.ui.OnlineCounterProps
import com.bytelegend.client.app.ui.ScrollButtonsLayer
import com.bytelegend.client.app.ui.ScrollButtonsProps
import com.bytelegend.client.app.ui.SpriteNameWidget
import com.bytelegend.client.app.ui.SpriteNameWidgetProps
import com.bytelegend.client.app.ui.StarCountWidget
import com.bytelegend.client.app.ui.StarCountWidgetProps
import com.bytelegend.client.app.ui.TileCursorWidget
import com.bytelegend.client.app.ui.TileCursorWidgetProps
import com.bytelegend.client.app.ui.ToastUIComponent
import com.bytelegend.client.app.ui.UserAvatarWidget
import com.bytelegend.client.app.ui.UserAvatarWidgetProps
import com.bytelegend.client.app.ui.UserMouseInteractionLayer
import com.bytelegend.client.app.ui.UserMouseInteractionLayerProps
import com.bytelegend.client.app.ui.gameChild
import com.bytelegend.client.app.ui.menu.Menu
import com.bytelegend.client.app.ui.menu.MenuProps
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.serialization.json.Json
import react.RBuilder
import react.RComponent
import react.RElementBuilder
import react.RProps
import react.RState
import react.ReactElement
import react.dom.RDOMBuilder
import react.dom.div
import react.dom.render
import react.setState

val GAME_INIT_DATA: GameInitData = Json { ignoreUnknownKeys = true }.decodeFromString(
    GameInitData.serializer(),
    window.asDynamic().serverSideData
)

val HERO_AVATAR_IMG_ID = "hero-avatar"

val game = init(GAME_INIT_DATA).apply {
    window.asDynamic().gameRuntime = this
}

fun main() {
    render(document.getElementById("app")) {
        child(GamePage::class) {
        }
    }
}

interface GamePageState : RState {
    var loading: Boolean
}

interface GamePageProps : RProps

class GamePage : RComponent<GamePageProps, GamePageState>() {
    override fun GamePageState.init() {
        loading = true
    }

    init {
        loadResourcesAndStart()
        window.onresize = { onWindowResize() }
    }

    private fun loadResourcesAndStart() {
        game.resourceLoader.loadAsync(
            I18nTextResource(
                "common-${game.locale.toLowerCase()}",
                game.resolve("/i18n/common/${game.locale.toLowerCase()}.json"),
                1,
                game.i18nTextContainer
            )
        )
        game.resourceLoader.loadAsync(ImageResource("texture", game.resolve("/img/ui/texture.jpg"), 1))
        game.resourceLoader.loadAsync(AudioResource("forest", game.resolve("/audio/forest.ogg"), 1), false)
        game.resourceLoader.loadAsync(AudioResource("starfly", game.resolve("/audio/starfly.mp3"), 1), false)
        game.webSocketClient.self = game.resourceLoader.loadAsync(game.webSocketClient)

        if (game.heroPlayer.isAnonymous) {
            game.sceneContainer.loadScene(GAME_INIT_DATA.initMapId) { _, _ ->
                game.start()
            }
        } else {
            val animationSetId = playerAnimationSetResourceId(GAME_INIT_DATA.player.characterId!!)
            val animationSetDeferred = game.resourceLoader.loadAsync(
                ImageResource(
                    animationSetId,
                    game.resolve("/img/player/$animationSetId.png"),
                    1
                )
            )
            game.resourceLoader.loadAsync(
                ImageResource(
                    HERO_AVATAR_IMG_ID,
                    game.heroPlayer.avatarUrl!!,
                    0
                ),
                false
            )

            game.sceneContainer.loadScene(GAME_INIT_DATA.player.map) { _, newScene ->
                animationSetDeferred.await()

                val obj = HeroCharacter(newScene, GAME_INIT_DATA.player)
                game._hero = obj
                obj.init()
                newScene.objects.add(obj)
                game.start()

//                window.setTimeout({
//                    val canvasState = game.activeScene.canvasState
//                    val endCoordinateInGameContainer: PixelCoordinate = canvasState.determineStarCountWidgetCoordinateInGameContainerLeftTop()
//                    val startCoordinateInGameContainer: PixelCoordinate = canvasState.determineMenuCoordinateInGameContainer()
//                    GlobalScope.launch {
//                        getAudioElementOrNull("starfly")?.apply {
//                            loop = false
//                            play()
//                        }
//                        starFlyEffect(
//                            canvasState.gameContainerSize,
//                            startCoordinateInGameContainer,
//                            endCoordinateInGameContainer,
//                            3
//                        )
//                        game.eventBus.emit(STAR_INCREMENT_EVENT, StarUpdateEventData(
//                            "","","", 1,2
//                        ))
//                    }
//                }, 5000)
            }
        }
    }

    private fun onWindowResize() {
        if (!state.loading) {
            // Do nothing if page is still loading
            game.gameContainerSize = PixelSize(window.innerWidth, window.innerHeight)
            game.eventBus.emit(GAME_UI_UPDATE_EVENT, null)
        }
    }

    override fun RBuilder.render() {
        if (state.loading) {
            child(LoadingPage::class) {
                attrs.eventBus = game.eventBus
            }
        } else {
            div {
                gameContainer(game) {
                    heroIndicator(attrs)
                    modalController(attrs)
                    bannerController(attrs)
                    toastController(attrs)
                    userAvatarWidget(attrs)
                    icpServerLocationWidget(attrs)
                    gameScriptWidgetDisplayLayer(attrs)
                    scrollButtons(attrs)
                    userMouseInteractionLayer(attrs)
                    mapTitleWidgets(Direction.LEFT, attrs) {
                        mapNameWidget(attrs)
                        fpsCounter(attrs)
                        onlineCounter(attrs)
                        mapCoordinateTitleWidget(attrs)
                    }
                    mapTitleWidgets(Direction.RIGHT, attrs) {
                        audioSwitch(attrs)
                        localeSelectionDropdown(attrs)
                    }
                    starCountWidget(attrs)
                    tileCursorWidget(attrs)
                    spriteNameWidget(attrs)
                    miniMapCanvas(attrs)
                    mapCanvas(attrs)

                    menu(attrs)
                }
            }
        }
    }

    private val gameUiUpdateEventListener: EventListener<Nothing> = {
        setState { }
    }
    private val sceneLoadingStartEventListener: EventListener<Nothing> = {
        setState { loading = true }
    }
    private val sceneLoadingEndEventListener: EventListener<Nothing> = {
        setState { loading = false }
    }

    override fun componentDidMount() {
        game.eventBus.on(GAME_UI_UPDATE_EVENT, gameUiUpdateEventListener)
        game.eventBus.on(SCENE_LOADING_START_EVENT, sceneLoadingStartEventListener)
        game.eventBus.on(SCENE_LOADING_END_EVENT, sceneLoadingEndEventListener)
    }

    override fun componentWillUnmount() {
        game.eventBus.remove(GAME_UI_UPDATE_EVENT, gameUiUpdateEventListener)
        game.eventBus.remove(SCENE_LOADING_START_EVENT, sceneLoadingStartEventListener)
        game.eventBus.remove(SCENE_LOADING_END_EVENT, sceneLoadingEndEventListener)
    }

    private fun RDOMBuilder<*>.gameContainer(game: Game, block: RElementBuilder<GameContainerProps>.() -> Unit = {}) {
        child(GameContainer::class) {
            attrs.game = game
            block()
        }
    }

    fun RElementBuilder<GameContainerProps>.scrollButtons(
        parentProps: GameContainerProps,
        block: RElementBuilder<ScrollButtonsProps>.() -> Unit = {}
    ) = gameChild(parentProps, ScrollButtonsLayer::class, block)

    fun RElementBuilder<GameContainerProps>.userMouseInteractionLayer(
        parentProps: GameContainerProps,
        block: RElementBuilder<UserMouseInteractionLayerProps>.() -> Unit = {}
    ) = gameChild(parentProps, UserMouseInteractionLayer::class, block)

    fun RElementBuilder<GameContainerProps>.gameScriptWidgetDisplayLayer(
        parentProps: GameContainerProps,
        block: RElementBuilder<GameScriptWidgetDisplayLayerProps>.() -> Unit = {}
    ) = gameChild(parentProps, GameScriptWidgetDisplayLayer::class, block)

//    fun RBuilder.informationDisplayLayer(block: RElementBuilder<InformationDisplayLayerProps>.() -> Unit) = layer(InformationDisplayLayer::class, Layer.InformationDisplay, block)
//
//    fun RBuilder.interactableWidgetsLayer(block: RElementBuilder<InteractableWidgetsLayerProps>.() -> Unit) = layer(InteractableWidgetsLayer::class, Layer.InteractableWidgets, block)

    fun RElementBuilder<GameContainerProps>.mapCanvas(
        parentProps: GameContainerProps,
        block: RElementBuilder<MapCanvasProps>.() -> Unit = {}
    ) = gameChild(parentProps, MainMapCanvasLayer::class, block)

    fun RElementBuilder<GameContainerProps>.miniMapCanvas(
        parentProps: GameContainerProps,
        block: RElementBuilder<MapCanvasProps>.() -> Unit = {}
    ) = gameChild(parentProps, MiniMapCanvasLayer::class, block)

    fun RElementBuilder<GameContainerProps>.mapTitleWidgets(
        direction: Direction,
        parentProps: GameContainerProps,
        block: RElementBuilder<MapTitleWidgetsProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, MapTitleWidgets::class) {
            attrs.direction = direction
            block()
        }
    }

    fun RElementBuilder<GameContainerProps>.starCountWidget(
        parentProps: GameContainerProps,
        block: RElementBuilder<StarCountWidgetProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, StarCountWidget::class, block)
    }

    fun RElementBuilder<GameContainerProps>.tileCursorWidget(
        parentProps: GameContainerProps,
        block: RElementBuilder<TileCursorWidgetProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, TileCursorWidget::class, block)
    }

    fun RElementBuilder<GameContainerProps>.spriteNameWidget(
        parentProps: GameContainerProps,
        block: RElementBuilder<SpriteNameWidgetProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, SpriteNameWidget::class, block)
    }

    fun RElementBuilder<MapTitleWidgetsProps>.mapNameWidget(
        parentProps: MapTitleWidgetsProps,
        block: RElementBuilder<MapSelectionDropdownProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, MapSelectionDropdown::class, block)
    }

    fun RElementBuilder<MapTitleWidgetsProps>.fpsCounter(
        parentProps: MapTitleWidgetsProps,
        block: RElementBuilder<FpsCounterProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, FpsCounter::class, block)
    }

    fun RElementBuilder<MapTitleWidgetsProps>.onlineCounter(parentProps: MapTitleWidgetsProps, block: RElementBuilder<OnlineCounterProps>.() -> Unit = {}): ReactElement {
        return gameChild(parentProps, OnlineCounter::class, block)
    }

    fun RElementBuilder<MapTitleWidgetsProps>.mapCoordinateTitleWidget(
        parentProps: MapTitleWidgetsProps,
        block: RElementBuilder<MapCoordinateWidgetProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, MapCoordinateTitleWidget::class, block)
    }

    private fun RElementBuilder<MapTitleWidgetsProps>.audioSwitch(
        parentProps: MapTitleWidgetsProps,
        block: RElementBuilder<AudioSwitchWidgetProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, AudioSwitchWidget::class, block)
    }

    private fun RElementBuilder<MapTitleWidgetsProps>.localeSelectionDropdown(
        parentProps: MapTitleWidgetsProps,
        block: RElementBuilder<LocaleSelectionDropdownProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, LocaleSelectionDropdown::class, block)
    }

    private fun RElementBuilder<GameContainerProps>.menu(
        parentProps: GameContainerProps,
        block: RElementBuilder<MenuProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, Menu::class, block)
    }

    private fun RElementBuilder<GameContainerProps>.heroIndicator(
        parentProps: GameContainerProps,
        block: RElementBuilder<GameProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, HeroIndicatorWidget::class, block)
    }

    private fun RElementBuilder<GameContainerProps>.modalController(
        parentProps: GameContainerProps,
        block: RElementBuilder<GameProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, GameModal::class, block)
    }

    private fun RElementBuilder<GameContainerProps>.bannerController(
        parentProps: GameContainerProps,
        block: RElementBuilder<GameProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, BannerUIComponent::class, block)
    }

    private fun RElementBuilder<GameContainerProps>.toastController(
        parentProps: GameContainerProps,
        block: RElementBuilder<GameProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, ToastUIComponent::class, block)
    }

    private fun RElementBuilder<GameContainerProps>.userAvatarWidget(
        parentProps: GameContainerProps,
        block: RElementBuilder<UserAvatarWidgetProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, UserAvatarWidget::class, block)
    }

    private fun RElementBuilder<GameContainerProps>.icpServerLocationWidget(
        parentProps: GameContainerProps,
        block: RElementBuilder<GameProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, ICPServerLocationWidget::class, block)
    }
}
