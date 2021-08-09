package com.bytelegend.client.app.ui

enum class Layer {
    MapCanvas,
    CursorWidget,
    PlayerNames,
    UserMouseInteraction,
    FloatingTitle,
    MissionTitlePullRequestAnswerButton,
    ScrollButtons,
    HeroControlButton,
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
