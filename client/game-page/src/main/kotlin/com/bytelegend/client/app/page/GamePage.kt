/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("DeferredResultUnused", "UnsafeCastFromDynamic")

package com.bytelegend.client.app.page

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.WindowBasedEventBus
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GameInitData
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.playerAnimationSetResourceId
import com.bytelegend.client.app.engine.BrowserConsoleLogger
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.engine.Game
import com.bytelegend.client.app.engine.SCENE_LOADING_END_EVENT
import com.bytelegend.client.app.engine.SCENE_LOADING_START_EVENT
import com.bytelegend.client.app.engine.init
import com.bytelegend.client.app.engine.resource.AudioResource
import com.bytelegend.client.app.engine.resource.I18nTextResource
import com.bytelegend.client.app.engine.resource.ImageResource
import com.bytelegend.client.app.obj.character.HeroCharacter
import com.bytelegend.client.app.ui.AudioSwitchWidget
import com.bytelegend.client.app.ui.BannerUIComponent
import com.bytelegend.client.app.ui.CoinCountWidget
import com.bytelegend.client.app.ui.CoinCountWidgetProps
import com.bytelegend.client.app.ui.FpsCounter
import com.bytelegend.client.app.ui.GameContainer
import com.bytelegend.client.app.ui.GameContainerProps
import com.bytelegend.client.app.ui.GameModal
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameScriptWidgetDisplayLayer
import com.bytelegend.client.app.ui.GameScriptWidgetDisplayLayerProps
import com.bytelegend.client.app.ui.HeroControlButton
import com.bytelegend.client.app.ui.HeroIndicatorWidget
import com.bytelegend.client.app.ui.ICPServerLocationWidget
import com.bytelegend.client.app.ui.LivestreamIndicators
import com.bytelegend.client.app.ui.LocaleSelectionDropdown
import com.bytelegend.client.app.ui.LocaleSelectionDropdownProps
import com.bytelegend.client.app.ui.MainMapCanvasLayer
import com.bytelegend.client.app.ui.MapCanvasProps
import com.bytelegend.client.app.ui.MapCoordinateTitleWidget
import com.bytelegend.client.app.ui.MapSelectionDropdown
import com.bytelegend.client.app.ui.MapSelectionDropdownProps
import com.bytelegend.client.app.ui.MapTitleWidgets
import com.bytelegend.client.app.ui.MapTitleWidgetsProps
import com.bytelegend.client.app.ui.MiniMap
import com.bytelegend.client.app.ui.MissionItemButtons
import com.bytelegend.client.app.ui.OnlineCounter
import com.bytelegend.client.app.ui.OnlineCounterProps
import com.bytelegend.client.app.ui.PlayerNames
import com.bytelegend.client.app.ui.PlayerNamesProps
import com.bytelegend.client.app.ui.ReputationCountWidget
import com.bytelegend.client.app.ui.ReputationCountWidgetProps
import com.bytelegend.client.app.ui.RightSideBar
import com.bytelegend.client.app.ui.RightSideBarProps
import com.bytelegend.client.app.ui.ScrollButtonsLayer
import com.bytelegend.client.app.ui.ScrollButtonsProps
import com.bytelegend.client.app.ui.StarCountWidget
import com.bytelegend.client.app.ui.StarCountWidgetProps
import com.bytelegend.client.app.ui.TileCursorWidget
import com.bytelegend.client.app.ui.ToastUIComponent
import com.bytelegend.client.app.ui.UserAvatarWidget
import com.bytelegend.client.app.ui.UserMouseInteractionLayer
import com.bytelegend.client.app.ui.achievement.AchievementWidget
import com.bytelegend.client.app.ui.gameChild
import com.bytelegend.client.app.ui.item.ItemsWidget
import com.bytelegend.client.app.ui.menu.Menu
import com.bytelegend.client.app.ui.menu.MenuProps
import com.bytelegend.client.app.ui.mission.BouncingTitles
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.utils.toGameInitData
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.js.jso
import react.ChildrenBuilder
import react.Component
import react.Fragment
import react.ReactNode
import react.State
import react.create
import react.dom.client.createRoot
import react.react

const val HERO_AVATAR_IMG_ID = "hero-avatar"

fun main() {
    if (window.asDynamic().gameInitData == null) {
        // this is necessary for unit test
        console.warn("Game init data not found, abort loading.")
        return
    }
    val gameInitData: GameInitData = toGameInitData(window.asDynamic().gameInitData)
    val game = init(gameInitData).apply {
        window.asDynamic().gameRuntime = this
    }
    window.onerror = { a: dynamic, b: String, c: Int, d: Int, e: Any? ->
        BrowserConsoleLogger.error("$a $b $c $d $e")
    }
    window.addEventListener("message", {
        val event = it.asDynamic()
        if (event.data.bytelegendEvent) {
            WindowBasedEventBus.emit(event.data.bytelegendEvent, event.data.bytelegendEventPayload)
        }
    })

    // TODO make this js-load-order-agnostic
    // https://github.com/PrismJS/prism/issues/1764
    window.asDynamic().Prism.hooks.add("before-highlight") { env: dynamic ->
        env.code = env.element.innerText
        null
    }

    createRoot(document.getElementById("app")!!)
        .render(Fragment.create {
            child(GamePage::class.react, jso {
                this.game = game
            })
        })
}

interface GamePageState : State {
    var sceneLoading: Boolean
}

class GamePage(props: GameProps) : Component<GameProps, GamePageState>(props) {
    init {
        state = jso {
            sceneLoading = true
        }
        loadResourcesAndStart()
        window.onresize = { onWindowResize() }
    }

    private val game: Game
        get() = props.game

    private fun loadResourcesAndStart() {
        game.resourceLoader.loadAsync(
            I18nTextResource(
                "common-${game.locale.lowercase()}",
                game.resolve("/i18n/common/${game.locale.lowercase()}.json"),
                game.i18nTextContainer
            )
        )

        if (!game.heroPlayer.isAnonymous) {
            game.resourceLoader.loadAsync(AudioResource("coin", game.resolve("/audio/coin.mp3")), false)
            game.resourceLoader.loadAsync(AudioResource("coin-loss", game.resolve("/audio/coin-loss.mp3")), false)
            game.resourceLoader.loadAsync(AudioResource("starfly", game.resolve("/audio/starfly.mp3")), false)
            game.resourceLoader.loadAsync(AudioResource("popup", game.resolve("/audio/popup.mp3")), false)
            game.resourceLoader.loadAsync(AudioResource("achievement", game.resolve("/audio/achievement.mp3")), false)
            game.resourceLoader.loadAsync(AudioResource("tada", game.resolve("/audio/tada.mp3")), false)
        }
        game.webSocketClient.self = game.resourceLoader.loadAsync(game.webSocketClient)

        if (game.heroPlayer.isAnonymous) {
            game.sceneContainer.loadScene(game.gameInitData.initMapId) { _, _ ->
                game.start()
            }
        } else {
            val animationSetId = playerAnimationSetResourceId(game.gameInitData.player.characterId)
            val animationSetDeferred = game.resourceLoader.loadAsync(
                ImageResource(
                    animationSetId,
                    game.resolve("/img/player/$animationSetId.png")
                )
            )
            game.resourceLoader.loadAsync(
                ImageResource(
                    HERO_AVATAR_IMG_ID,
                    game.heroPlayer.avatarUrl!!
                ),
                false
            )

            game.sceneContainer.loadScene(game.gameInitData.player.map) { _, newScene ->
                animationSetDeferred.await()

                game._hero = HeroCharacter(newScene, game.gameInitData.player).apply { init() }
                game.start()
            }
        }
    }

    private fun onWindowResize() {
        if (!state.sceneLoading) {
            // Do nothing if page is still loading
            game.gameContainerSize = PixelSize(window.innerWidth, window.innerHeight)
            game.eventBus.emit(GAME_UI_UPDATE_EVENT, null)
        }
    }

    override fun render(): ReactNode = Fragment.create {
        if (state.sceneLoading) {
            // some global resources like `common-en.json` still requires to be loaded
            // GameScene.load() doesn't take this into consideration
            child(LoadingPage::class.react, jso {
                this.game = props.game
                eventBus = game.eventBus
            })
        } else {
            gameContainer(game) {
                heroIndicator(this)
                modalController(this)
                bannerController(this)
                toastController(this)
                userAvatarWidget(this)
                icpServerLocationWidget(this)
                gameScriptWidgetDisplayLayer(this)
                scrollButtons(this)
                userMouseInteractionLayer(this)
                mapTitleWidgets(Direction.LEFT, this) {
                    mapNameWidget(this)
                    fpsCounter(this)
                    onlineCounter(this)
                    mapCoordinateTitleWidget(this)
                }
                mapTitleWidgets(Direction.RIGHT, this) {
                    audioSwitch(this)
                    localeSelectionDropdown(this)
                }
                rightSideBarWidgets(this) {
                    starCountWidget(this)
                    coinCountWidget(this)
                    reputationCountWidget(this)
                    itemWidget(this)
                    achievementWidget(this)
                }
                livestreamIndicators(this)
                bouncingTitleWidgets(this)
                missionItemButtons(this)
                tileCursorWidget(this)
                heroControlButton(this)
                spriteNameWidget(this)
                miniMap(this)
                mapCanvas(this)

                menu(this)
            }
        }
    }

    private val gameUiUpdateEventListener: EventListener<Nothing> = {
        setState { }
    }
    private val sceneLoadingStartEventListener: EventListener<Nothing> = {
        setState { sceneLoading = true }
    }
    private val sceneLoadingEndEventListener: EventListener<Nothing> = {
        setState { sceneLoading = false }
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

    private fun ChildrenBuilder.gameContainer(game: Game, block: GameContainerProps.() -> Unit = {}) {
        child(GameContainer::class.react, jso {
            this.game = game
            block()
        })
    }

    private fun heroIndicator(
        parentProps: GameContainerProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, HeroIndicatorWidget::class.react, block)
    }

    private fun scrollButtons(
        parentProps: GameContainerProps,
        block: ScrollButtonsProps.() -> Unit = {}
    ) = gameChild(parentProps, ScrollButtonsLayer::class.react, block)

    private fun userMouseInteractionLayer(
        parentProps: GameContainerProps,
        block: GameProps.() -> Unit = {}
    ) = gameChild(parentProps, UserMouseInteractionLayer::class.react, block)

    private fun gameScriptWidgetDisplayLayer(
        parentProps: GameContainerProps,
        block: GameScriptWidgetDisplayLayerProps.() -> Unit = {}
    ) = gameChild(parentProps, GameScriptWidgetDisplayLayer::class.react, block)

    private fun mapCanvas(
        parentProps: GameContainerProps,
        block: GameProps.() -> Unit = {}
    ) = gameChild(parentProps, MainMapCanvasLayer::class.react, block)

    private fun miniMap(
        parentProps: GameContainerProps,
        block: MapCanvasProps.() -> Unit = {}
    ) = gameChild(parentProps, MiniMap::class.react, block)

    private fun mapTitleWidgets(
        direction: Direction,
        parentProps: GameContainerProps,
        block: MapTitleWidgetsProps.() -> Unit = {}
    ) {
        gameChild(parentProps, MapTitleWidgets::class.react) {
            this.direction = direction
            block()
        }
    }

    private fun rightSideBarWidgets(
        parentProps: GameContainerProps,
        block: RightSideBarProps.() -> Unit = {}
    ) {
        return gameChild(parentProps, RightSideBar::class.react) {
            block()
        }
    }

    private fun starCountWidget(
        parentProps: RightSideBarProps,
        block: StarCountWidgetProps.() -> Unit = {}
    ) {
        return gameChild(parentProps, StarCountWidget::class.react, block)
    }

    private fun coinCountWidget(
        parentProps: RightSideBarProps,
        block: CoinCountWidgetProps.() -> Unit = {}
    ) {
        gameChild(parentProps, CoinCountWidget::class.react, block)
    }

    private fun reputationCountWidget(
        parentProps: RightSideBarProps,
        block: ReputationCountWidgetProps.() -> Unit = {}
    ) {
        gameChild(parentProps, ReputationCountWidget::class.react, block)
    }

    private fun itemWidget(
        parentProps: RightSideBarProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, ItemsWidget::class.react, block)
    }

    private fun achievementWidget(
        parentProps: RightSideBarProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, AchievementWidget::class.react, block)
    }

    private fun tileCursorWidget(
        parentProps: GameContainerProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, TileCursorWidget::class.react, block)
    }

    private fun heroControlButton(
        parentProps: GameContainerProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, HeroControlButton::class.react, block)
    }

    private fun livestreamIndicators(
        parentProps: GameContainerProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, LivestreamIndicators::class.react, block)
    }

    private fun bouncingTitleWidgets(
        parentProps: GameContainerProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, BouncingTitles::class.react, block)
    }

    private fun missionItemButtons(
        parentProps: GameContainerProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, MissionItemButtons::class.react, block)
    }

    private fun spriteNameWidget(
        parentProps: GameContainerProps,
        block: PlayerNamesProps.() -> Unit = {}
    ) {
        gameChild(parentProps, PlayerNames::class.react, block)
    }

    private fun mapNameWidget(
        parentProps: MapTitleWidgetsProps,
        block: MapSelectionDropdownProps.() -> Unit = {}
    ) {
        gameChild(parentProps, MapSelectionDropdown::class.react, block)
    }

    private fun fpsCounter(
        parentProps: MapTitleWidgetsProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, FpsCounter::class.react, block)
    }

    private fun onlineCounter(parentProps: MapTitleWidgetsProps, block: OnlineCounterProps.() -> Unit = {}) {
        gameChild(parentProps, OnlineCounter::class.react) {
            initCount = game.onlineNumber
            block()
        }
    }

    private fun mapCoordinateTitleWidget(
        parentProps: MapTitleWidgetsProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, MapCoordinateTitleWidget::class.react, block)
    }

    private fun audioSwitch(
        parentProps: MapTitleWidgetsProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, AudioSwitchWidget::class.react, block)
    }

    private fun localeSelectionDropdown(
        parentProps: MapTitleWidgetsProps,
        block: LocaleSelectionDropdownProps.() -> Unit = {}
    ) {
        gameChild(parentProps, LocaleSelectionDropdown::class.react, block)
    }

    private fun menu(
        parentProps: GameContainerProps,
        block: MenuProps.() -> Unit = {}
    ) {
        gameChild(parentProps, Menu::class.react, block)
    }

    private fun modalController(
        parentProps: GameContainerProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, GameModal::class.react, block)
    }

    private fun bannerController(
        parentProps: GameContainerProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, BannerUIComponent::class.react, block)
    }

    private fun toastController(
        parentProps: GameContainerProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, ToastUIComponent::class.react, block)
    }

    private fun userAvatarWidget(
        parentProps: GameContainerProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, UserAvatarWidget::class.react, block)
    }

    private fun icpServerLocationWidget(
        parentProps: GameContainerProps,
        block: GameProps.() -> Unit = {}
    ) {
        gameChild(parentProps, ICPServerLocationWidget::class.react, block)
    }
}
