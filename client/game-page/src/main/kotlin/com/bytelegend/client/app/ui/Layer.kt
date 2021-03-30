package com.bytelegend.client.app.ui

enum class Layer {
    GameContainer,
    MapCanvas,
    CursorWidget,
    UserMouseInteraction,
    ScrollButtons,
    MiniMapCanvas,
    ScriptWidget,
    StarWidget,
    UserAvatarWidget,
    LocaleSelectionDropdown,
    IcpServerLocationWidget,
    Menu,
    MapTitle,
    HeroIndicator,
    BannerToast,
    FadeInFadeOut;

    fun zIndex() = 10 * ordinal
}

interface ZIndexed {
    var zIndex: Int
}

interface Layered {
    var layer: Layer
}
