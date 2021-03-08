package com.bytelegend.client.app.ui

enum class Layer {
    GameContainer,
    MapCanvas,
    CursorWidget,
    UserMouseInteraction,
    ScrollButtons,
    MiniMapCanvas,
    ScriptWidget,
    UserAvatarWidget,
    LocaleSelectionDropdown,
    IcpServerLocationWidget,
    Menu,
    MapTitle,
    HeroIndicator,
    FadeInFadeOut,
    DisconnectionIndicator;

    fun zIndex() = 10 * ordinal
}

interface ZIndexed {
    var zIndex: Int
}

interface Layered {
    var layer: Layer
}
