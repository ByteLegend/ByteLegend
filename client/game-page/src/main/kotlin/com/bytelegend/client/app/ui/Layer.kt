package com.bytelegend.client.app.ui

enum class Layer {
    GameContainer,
    MapCanvas,
    PlayerNames,
    CursorWidget,
    UserMouseInteraction,
    ScrollButtons,
    MiniMapCanvas,
    ScriptWidget,
    RightSideBar,
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
