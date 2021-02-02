package com.bytelegend.client.app.page

import com.bytelegend.app.client.api.GameMapHierarchyResource
import com.bytelegend.app.client.api.I18nTextResource
import com.bytelegend.app.client.api.ImageResource
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.ServerSideData
import com.bytelegend.app.shared.animationSetId
import com.bytelegend.app.shared.playerAnimationSetResourceId
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.engine.Game
import com.bytelegend.client.app.engine.RESOURCE_LOADING_SUCCESS_EVENT
import com.bytelegend.client.app.engine.init
import com.bytelegend.client.app.obj.HeroCharacter
import com.bytelegend.client.app.ui.FadeInFadeOutLayer
import com.bytelegend.client.app.ui.FpsCounter
import com.bytelegend.client.app.ui.FpsCounterProps
import com.bytelegend.client.app.ui.GameContainer
import com.bytelegend.client.app.ui.GameContainerProps
import com.bytelegend.client.app.ui.GameModal
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.HeroIndicatorWidget
import com.bytelegend.client.app.ui.ICPServerLocationWidget
import com.bytelegend.client.app.ui.LocaleSelectionDropdown
import com.bytelegend.client.app.ui.LocaleSelectionDropdownProps
import com.bytelegend.client.app.ui.MapCanvasLayer
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
import com.bytelegend.client.app.ui.TileCursorWidget
import com.bytelegend.client.app.ui.TileCursorWidgetProps
import com.bytelegend.client.app.ui.UserAvatarWidget
import com.bytelegend.client.app.ui.UserAvatarWidgetProps
import com.bytelegend.client.app.ui.UserMouseInteractionLayer
import com.bytelegend.client.app.ui.UserMouseInteractionLayerProps
import com.bytelegend.client.app.ui.gameChild
import com.bytelegend.client.app.ui.menu.Menu
import com.bytelegend.client.app.ui.menu.MenuProps
import common.ui.CSSTransition
import common.ui.CSSTransitionProps
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

val SERVER_SIDE_DATA: ServerSideData = Json.decodeFromString(
    ServerSideData.serializer(),
    window.asDynamic().serverSideData
)

val HERO_AVATAR_IMG_ID = "hero-avatar"

val game = init(SERVER_SIDE_DATA).apply {
    window.asDynamic().gameRuntime = this
}

fun main() {
    render(document.getElementById("app")) {
        child(GamePage::class) {
        }
    }
}

interface GamePageState : RState

interface GamePageProps : RProps


class GamePage : RComponent<GamePageProps, GamePageState>() {
    init {
        addGlobalResources()

        game.sceneContainer.loadScene(SERVER_SIDE_DATA.mapId) { _, newScene ->
            game.start()
            if (!SERVER_SIDE_DATA.player.isAnonymous) {
                val obj = HeroCharacter(newScene, SERVER_SIDE_DATA.player)
                game._hero = obj
                obj.init()
            }
        }
        game.eventBus.on<Nothing>(GAME_UI_UPDATE_EVENT) {
            setState {}
        }
        game.eventBus.on<Any>(RESOURCE_LOADING_SUCCESS_EVENT) {
            setState { }
        }
        window.onresize = {
            if (!game.resourceLoader.isLoading()) {
                // Do nothing if page is still loading
                onWindowResize()
            }
            null
        }
    }

    private fun addGlobalResources() {
        console.log(1)
        console.log(game.player.isAnonymous)
        if (!game.player.isAnonymous) {
            val animationSetId = playerAnimationSetResourceId(animationSetId(SERVER_SIDE_DATA.player.characterId!!))
            game.resourceLoader.add(
                ImageResource(
                    animationSetId,
                    game.resolve("/img/player/$animationSetId.png"),
                    1
                )
            )
            game.resourceLoader.add(
                ImageResource(
                    HERO_AVATAR_IMG_ID,
                    game.player.avatarUrl!!,
                    0
                ),
                false
            )
        }
        console.log(2)
        game.resourceLoader.add(
            I18nTextResource(
                "common-${game.locale.toLowerCase()}",
                game.resolve("/i18n/common/${game.locale.toLowerCase()}.json"), 1
            )
        ) {
            game.i18nTextContainer.putAll(it)
        }
        console.log(3)
        game.resourceLoader.add(ImageResource("texture", game.resolve("/img/ui/texture.jpg"), 1))
        game.resourceLoader.add(GameMapHierarchyResource(game.resolve("/map/hierarchy.json"), 1))
        console.log(4)
    }

    private fun onWindowResize() {
        game.gameContainerSize = PixelSize(window.innerWidth, window.innerHeight)
        game.eventBus.emit(GAME_UI_UPDATE_EVENT, null)
    }

    override fun RBuilder.render() {
        fadeInFadeOutLayer(game)

        if (game.resourceLoader.isLoading()) {
            child(LoadingPage::class) {
                attrs.eventBus = game.eventBus
            }
        } else {
            div {
                gameContainer(game) {
                    heroIndicator(attrs)
                    modalController(attrs)
                    localeSelectionDropdown(attrs)
                    userAvatarWidget(attrs)
                    icpServerLocationWidget(attrs)
                    scrollButtons(attrs)
                    userMouseInteractionLayer(attrs)
                    mapTitleWidgets(attrs) {
                        mapNameWidget(attrs)
                        mapCoordinateTitleWidget(attrs)
                        fpsCounter(attrs)
//                                    onlineCounter(attrs)
                    }
                    tileCursorWidget(attrs)
                    spriteNameWidget(attrs)
                    miniMapCanvas(attrs)
                    mapCanvas(attrs)

                    menu(attrs)
                }
            }
//            }
        }
    }

    fun RBuilder.fadeInFadeOutLayer(
        game: Game,
    ): ReactElement {
        return child(FadeInFadeOutLayer::class) {
            attrs.game = game
        }
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

//    fun RBuilder.informationDisplayLayer(block: RElementBuilder<InformationDisplayLayerProps>.() -> Unit) = layer(InformationDisplayLayer::class, Layer.InformationDisplay, block)
//
//    fun RBuilder.interactableWidgetsLayer(block: RElementBuilder<InteractableWidgetsLayerProps>.() -> Unit) = layer(InteractableWidgetsLayer::class, Layer.InteractableWidgets, block)

    fun RElementBuilder<GameContainerProps>.mapCanvas(
        parentProps: GameContainerProps,
        block: RElementBuilder<MapCanvasProps>.() -> Unit = {}
    ) = gameChild(parentProps, MapCanvasLayer::class, block)

    fun RElementBuilder<GameContainerProps>.miniMapCanvas(
        parentProps: GameContainerProps,
        block: RElementBuilder<MapCanvasProps>.() -> Unit = {}
    ) = gameChild(parentProps, MiniMapCanvasLayer::class, block)

    fun RElementBuilder<GameContainerProps>.mapTitleWidgets(
        parentProps: GameContainerProps,
        block: RElementBuilder<MapTitleWidgetsProps>.() -> Unit = {}
    ): ReactElement {
        return gameChild(parentProps, MapTitleWidgets::class, block)
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

    private fun RElementBuilder<GameContainerProps>.localeSelectionDropdown(
        parentProps: GameContainerProps,
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

//    private fun RElementBuilder<InteractableWidgetsLayerProps>.dialogWidgets(
//        parentProps: InteractableWidgetsLayerProps,
//        block: RElementBuilder<DialogWidgetsProps>.() -> Unit = {}
//    ): ReactElement {
//        return gameChild(parentProps, DialogWidgets::class, block)
//    }

    private fun RBuilder.cssTransition(key: String, block: RElementBuilder<CSSTransitionProps>.() -> Unit) {
        CSSTransition {
            attrs.key = key
            attrs.timeout = 1000
            attrs.classNames = "item"
            block()
        }
    }
}
