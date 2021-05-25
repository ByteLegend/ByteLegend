package com.bytelegend.client.app.ui

enum class Layer {
    MapCanvas,
    PlayerNames,
    CursorWidget,
    UserMouseInteraction,
    MissionTitle,
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
