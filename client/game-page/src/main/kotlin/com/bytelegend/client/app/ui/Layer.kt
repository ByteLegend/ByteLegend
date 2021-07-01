package com.bytelegend.client.app.ui

enum class Layer {
    MapCanvas,
    // confetti, star flying particles, etc.
    EffectCanvas,
    CursorWidget,
    PlayerNames,
    UserMouseInteraction,
    MissionTitle,
    MissionTitlePullRequestAnswerButton,
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
