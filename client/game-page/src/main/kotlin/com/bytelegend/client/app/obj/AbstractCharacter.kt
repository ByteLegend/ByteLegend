package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.AbstractMovableSprite
import com.bytelegend.app.client.api.Character
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameSceneAware
import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PLAYER_LAYER
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.client.app.engine.GAME_CLOCK_50HZ_EVENT
import com.bytelegend.client.app.sprite.drawImage
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLImageElement

val DOWN_STILL = GridCoordinate(1, 0)
val DOWN_MOVE = listOf(GridCoordinate(0, 0), GridCoordinate(2, 0))
val UP_STILL = GridCoordinate(1, 3)
val UP_MOVE = listOf(GridCoordinate(0, 3), GridCoordinate(2, 3))
val LEFT_STILL = GridCoordinate(1, 1)
val LEFT_MOVE = listOf(GridCoordinate(0, 1), GridCoordinate(2, 1))
val RIGHT_STILL = GridCoordinate(1, 2)
val RIGHT_MOVE = listOf(GridCoordinate(0, 2), GridCoordinate(2, 2))


// How many pixels does a character move per second?
const val CHARACTER_MOVE_SPEED_PIXEL_PER_SECOND = 128
// Character move per 100 ms, to avoid millisecond precision issue
const val CHARACTER_MOVEMENT_MIN_INTERVAL_MS = 100
const val CHARACTER_ANIMATION_FPS = 2

data class AnimationSet(
    val htmlImageElement: HTMLImageElement,
    // The top-left corner pixel coordinate in the animation set.
    val coordinate: PixelCoordinate
)

@Suppress("UNUSED_PARAMETER")
abstract class AbstractCharacter(
    override val gameScene: GameScene,
    override var pixelCoordinate: PixelCoordinate,
    private val animationSet: AnimationSet
) : AbstractMovableSprite(), GameSceneAware, Character {
    override val layer: Int = PLAYER_LAYER

    var still = true

    override var direction: Direction = Direction.DOWN

    private val clockEventListener: EventListener<Nothing> = this::onAnimation

    override fun init() {
        gameScene.objects.add(this)
        gameScene.gameRuntime.eventBus.on(GAME_CLOCK_50HZ_EVENT, clockEventListener)
    }

    override fun close() {
        gameScene.objects.remove<GameObject>(this.id)
        gameScene.gameRuntime.eventBus.remove(GAME_CLOCK_50HZ_EVENT, clockEventListener)
    }

    override val gridCoordinate: GridCoordinate
        get() = pixelCoordinate / gameScene.map.tileSize

    // The time we update movement state last time
    private var lastAnimationTime: Timestamp? = null

    private var currentMovePathIndex: Int = -1

    /**
     * Current move path. When it's set, the character will start "moving" along this path
     * (i.e. responding to animation events and change coordinate correspondingly)
     */
    override var movePath: List<GridCoordinate> = emptyList()
        set(value) {
            field = value
            currentMovePathIndex = value.indexOf(gridCoordinate)
            require(currentMovePathIndex != -1) { "Setting new path. Current: $gridCoordinate, path: $value" }

            still = currentMovePathIndex == movePath.size - 1
            if (!still && lastAnimationTime == null) {
                lastAnimationTime = Timestamp.now()
            }
        }

    private fun elapsedSecondSinceLastAnimation() = (Timestamp.now() - lastAnimationTime!!) / 1000.0

    private fun onAnimation(unused: Nothing) {
        if (lastAnimationTime == null || Timestamp.now() - lastAnimationTime!! < CHARACTER_MOVEMENT_MIN_INTERVAL_MS) {
            return
        }

        var distanceToMove = (elapsedSecondSinceLastAnimation() * CHARACTER_MOVE_SPEED_PIXEL_PER_SECOND).toInt()

        // Let's move along movePath, tile by tile
        while (true) {
            val (currentDirection, distance) = directionAndDistanceToNextTileBorder()
            direction = currentDirection

            if (distanceToMove < distance) {
                // We don't need to move to next tile, just move
                pixelCoordinate = coordinatePlusPixel(pixelCoordinate, currentDirection, distanceToMove)
                lastAnimationTime = Timestamp.now()
                break
            } else {
                leaveTile(gridCoordinate)

                pixelCoordinate = coordinatePlusPixel(pixelCoordinate, currentDirection, distance)
                distanceToMove -= distance

                // Now step into a new tile
                enterTile(gridCoordinate)

                if (gridCoordinate == movePath.last()) {

                    // stop
                    still = true
                    lastAnimationTime = null
                    break
                }
            }
        }
    }

    open fun enterTile(gridCoordinate: GridCoordinate) {}

    open fun leaveTile(gridCoordinate: GridCoordinate) {}

    private fun nextCoordinateInPath(coordinateIndexInPath: Int) = movePath[coordinateIndexInPath + 1]

    /**
     * The moving direction and distance between character and next tile border.
     */
    private fun directionAndDistanceToNextTileBorder(): Pair<Direction, Int> {
        val coordinateIndexInPath = movePath.indexOf(gridCoordinate)

        require(coordinateIndexInPath != -1)
        require(pixelCoordinate.x % tileSize.width == 0 || pixelCoordinate.y % tileSize.height == 0)

        if (pixelCoordinate.x % tileSize.width == 0 && pixelCoordinate.y % tileSize.width == 0) {
            // at tile border, change direction
            return if (coordinateIndexInPath == movePath.size - 1) {
                // This should not happen.
                direction to 0
            } else {
                val nextTilePixelCoordinate = movePath[coordinateIndexInPath + 1] * tileSize
                directionBetweenCurrentAndNext(pixelCoordinate, nextTilePixelCoordinate) to pixelCoordinate.manhattanDistanceTo(nextTilePixelCoordinate)
            }
        } else {
            // move to next tile border first
            return if (pixelCoordinate.x % tileSize.width == 0) {
                // moving up-down
                when (direction) {
                    Direction.UP -> Direction.UP to pixelCoordinate.y % tileSize.height
                    // You're moving down, but next tile is not at downside, if you keep moving down, you'll be out of the path :-(
                    Direction.DOWN -> {
                        if (coordinateIndexInPath != movePath.size - 1 &&
                            nextCoordinateInPath(coordinateIndexInPath).x == gridCoordinate.x &&
                            nextCoordinateInPath(coordinateIndexInPath).y > gridCoordinate.y
                        ) {
                            // Very nice! next tile is just at the direction you're currently moving, keep moving!
                            Direction.DOWN to tileSize.height - pixelCoordinate.y % tileSize.height
                        } else {
                            // Sorry, next tile is not at the direction you're moving, you have to go back
                            Direction.UP to pixelCoordinate.y % tileSize.height
                        }
                    }
                    else -> throw IllegalStateException("Invalid coordinate: $pixelCoordinate")
                }
            } else {
                when (direction) {
                    Direction.LEFT -> Direction.LEFT to pixelCoordinate.x % tileSize.width
                    Direction.RIGHT -> {
                        if (coordinateIndexInPath != movePath.size - 1 &&
                            nextCoordinateInPath(coordinateIndexInPath).y == gridCoordinate.y &&
                            nextCoordinateInPath(coordinateIndexInPath).x > gridCoordinate.x
                        ) {
                            // Very nice! next tile is just at the direction you're currently moving, keep moving!
                            Direction.RIGHT to tileSize.width - pixelCoordinate.x % tileSize.width
                        } else {
                            // Sorry, next tile is not at the direction you're moving, you have to go back
                            Direction.LEFT to pixelCoordinate.x % tileSize.width
                        }
                    }
                    else -> throw IllegalStateException("Invalid coordinate: $pixelCoordinate")
                }
            }
        }
    }

    private fun draw(canvas: CanvasRenderingContext2D, coordinateIn12Set: GridCoordinate) {
        val frame = animationSet.coordinate + coordinateIn12Set * tileSize
        canvas.drawImage(
            animationSet.htmlImageElement,
            PixelBlock(frame, tileSize),
            getImageBlockOnCanvas()
        )
    }

    override fun draw(canvas: CanvasRenderingContext2D) {
        if (still) {
            when (direction) {
                Direction.UP -> draw(canvas, UP_STILL)
                Direction.DOWN -> draw(canvas, DOWN_STILL)
                Direction.LEFT -> draw(canvas, LEFT_STILL)
                Direction.RIGHT -> draw(canvas, RIGHT_STILL)
                else -> throw IllegalStateException()
            }
        } else {
            when (direction) {
                Direction.UP -> draw(canvas, animationFrameOf(UP_MOVE))
                Direction.DOWN -> draw(canvas, animationFrameOf(DOWN_MOVE))
                Direction.LEFT -> draw(canvas, animationFrameOf(LEFT_MOVE))
                Direction.RIGHT -> draw(canvas, animationFrameOf(RIGHT_MOVE))
                else -> throw IllegalStateException()
            }
        }
    }

    private fun animationFrameOf(frames: List<GridCoordinate>): GridCoordinate {
        // ms/(1000/fps) % frameSize
        //
        // Given 2-frame animation
        // 2 fps:
        //   0~499   -> 0
        //   500~999 -> 1
        // 4 fps:
        //   0~249   -> 0
        //   250~499 -> 1
        //   500-749 -> 0
        //   750-999 -> 1
        val frameIndex = (gameScene.gameRuntime.currentTimeMillis * CHARACTER_ANIMATION_FPS / 1000).toInt() % frames.size
        return frames[frameIndex]
    }

    private fun directionBetweenCurrentAndNext(currentCoordinate: PixelCoordinate, nextCoordinate: PixelCoordinate) = when {
        currentCoordinate.x == nextCoordinate.x && currentCoordinate.y < nextCoordinate.y -> Direction.DOWN
        currentCoordinate.x == nextCoordinate.x && currentCoordinate.y > nextCoordinate.y -> Direction.UP
        currentCoordinate.y == nextCoordinate.y && currentCoordinate.x > nextCoordinate.x -> Direction.LEFT
        currentCoordinate.y == nextCoordinate.y && currentCoordinate.x < nextCoordinate.x -> Direction.RIGHT
        currentCoordinate.x == nextCoordinate.x && currentCoordinate.y == nextCoordinate.y -> direction // this should not happen...
        else -> throw IllegalStateException("Illegal coordinates: $currentCoordinate $nextCoordinate")
    }

    private fun coordinatePlusPixel(coordinate: PixelCoordinate, direction: Direction, distance: Int) = when (direction) {
        Direction.UP -> PixelCoordinate(coordinate.x, coordinate.y - distance)
        Direction.DOWN -> PixelCoordinate(coordinate.x, coordinate.y + distance)
        Direction.LEFT -> PixelCoordinate(coordinate.x - distance, coordinate.y)
        Direction.RIGHT -> PixelCoordinate(coordinate.x + distance, coordinate.y)
        else -> throw IllegalStateException("Illegal direction: $direction")
    }
}
