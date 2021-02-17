package com.bytelegend.app.client.api.ui

// interface GameProps : RProps, GameRuntimeAware {
//    override var gameRuntime: GameRuntime
// }
//
// /**
// * A special component which responds to game.ui.update event and update itself correspondingly.
// */
// abstract class GameUIComponent<P : GameProps, S : RState> : RComponent<P, S>() {
//    protected val gameRuntime: GameRuntime
//        get() = props.gameRuntime
//
//    private val gameUiUpdateEventListener: EventListener<Any> = {
//        setState({ it })
//    }
//
//    override fun componentDidMount() {
//        props.gameRuntime.eventBus.on(GAME_UI_UPDATE_EVENT, gameUiUpdateEventListener)
//    }
//
//    override fun componentWillUnmount() {
//        props.gameRuntime.eventBus.remove(GAME_UI_UPDATE_EVENT, gameUiUpdateEventListener)
//    }
//
//    protected fun i(textId: String, vararg args: String) = props.gameRuntime.i(textId, *args)
// }
