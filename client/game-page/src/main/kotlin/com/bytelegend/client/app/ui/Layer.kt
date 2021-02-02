package com.bytelegend.client.app.ui

enum class Layer {
    GameContainer,
    MapCanvas,
    CursorWidget,
    UserMouseInteraction,
    GameScript,
    ScrollButtons,
    UserAvatarWidget,
    LocaleSelectionDropdown,
    MiniMapCanvas,
    IcpServerLocationWidget,
    Menu,
    MapTitle,
    HeroIndicator,
    FadeInFadeOut;

    fun zIndex() = 10 * ordinal
}

interface ZIndexed {
    var zIndex: Int
}

interface Layered {
    var layer: Layer
}
