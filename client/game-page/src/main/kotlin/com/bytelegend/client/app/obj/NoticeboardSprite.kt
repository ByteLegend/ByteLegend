package com.bytelegend.client.app.obj

// class NoticeboardSprite(
//    override val id: String,
//    override val gameScene: GameScene,
//    dynamicSprite: GameMapDynamicSprite
// ) : DynamicSprite(
//    id,
//    gameScene,
//    dynamicSprite,
//    RectangleOuterGlowEffect(3, 2, 58, 44, 20, "white")
// ) {
//    override fun init() {
//        super.init()
//        for (y in 0 until dynamicSprite.height) {
//            for (x in 0 until 2) {
//                val pointCoordinate = dynamicSprite.topLeftCorner + GridCoordinate(x, y)
//                gameScene.objects.add(
//                    GenericCoordinateAwareGameObject(
//                        "$id-click-point-$x-$y",
//                        pointCoordinate,
//                        pointCoordinate * gameScene.map.tileSize,
//                        onClickFunction = this::onClick,
//                        jsObjectBackedSetOf(
//                            GameObjectRole.CoordinateAware,
//                            GameObjectRole.Clickable,
//                        )
//                    )
//                )
//            }
//        }
//    }
//
//    override fun onClick() {
//        game.modalController.show {
//            child(JavaIslandNewbieVillageNoticeboard::class) {
//                attrs.game = game
//            }
//        }
//    }
// }
