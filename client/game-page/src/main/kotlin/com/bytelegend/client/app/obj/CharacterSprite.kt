package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.Character
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameSceneAware
import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.client.misc.search
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.NON_BLOCKER
import com.bytelegend.app.shared.PLAYER_LAYER
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.client.app.engine.GAME_CLOCK_50HZ_EVENT
import com.bytelegend.client.app.engine.logger
import org.w3c.dom.CanvasRenderingContext2D

// How many pixels does a character move per second?
const val CHARACTER_MOVE_SPEED_PIXEL_PER_SECOND = 128

// Character move per 100 ms, to avoid millisecond precision issue
const val CHARACTER_MOVEMENT_MIN_INTERVAL_MS = 100
const val CHARACTER_ANIMATION_FPS = 2

@Suppress("UNUSED_PARAMETER")
abstract class CharacterSprite(
    override val gameScene: GameScene,
    override var pixelCoordinate: PixelCoordinate,
    private val animationSet: AnimationSet
) : AbstractSprite(), GameSceneAware, Character {
    override val layer: Int = PLAYER_LAYER

    var still = true

    override var direction: Direction = Direction.DOWN

    private val clockEventListener: EventListener<Nothing> = this::onAnimation

    override fun init() {
        gameScene.objects.add(this)
        gameScene.gameRuntime.eventBus.on(GAME_CLOCK_50HZ_EVENT, clockEventListener)
    }

    override fun close() {
        gameScene.objects.remove<GameObject>(id)
        gameScene.gameRuntime.eventBus.remove(GAME_CLOCK_50HZ_EVENT, clockEventListener)
    }

    override var gridCoordinate: GridCoordinate
        get() = pixelCoordinate / gameScene.map.tileSize
        set(value) {
            require(movePath.isEmpty()) {
                "Setting grid coordinate when character is moving!"
            }
            // leave old tile and enter new tile.
            pixelCoordinate = value * gameScene.map.tileSize
        }

    // The time we update movement state last time
    private var lastAnimationTime: Timestamp? = null

    private var currentMovePathIndex: Int = -1

    /**
     * When set, the character will invoke the callback
     * upon destination.
     */
    private var callbackOnDestination: UnitFunction? = null

    /**
     * Current move path. When it's set, the character will start "moving" along this path
     * (i.e. responding to animation events and change coordinate correspondingly)
     */
    private var movePath: List<GridCoordinate> = emptyList()
        set(value) {
            field = value
            currentMovePathIndex = value.indexOf(gridCoordinate)
            require(currentMovePathIndex != -1) { "Setting new path. Current: $gridCoordinate, path: $value" }

            still = currentMovePathIndex == movePath.size - 1
            if (!still && lastAnimationTime == null) {
                lastAnimationTime = Timestamp.now()
            }
        }

    override fun moveAlong(movePath: List<GridCoordinate>, callback: UnitFunction?) {
        callbackOnDestination = callback
        this.movePath = movePath
    }

    override fun moveTo(destination: GridCoordinate, callback: UnitFunction?) {
        val path = searchPath(destination)
        if (path.isNotEmpty()) {
            logger.debug("$id starts moving along $path to $destination")
            moveAlong(path, callback)
        } else {
            logger.debug("$id can't move to $destination")
        }
    }

    override fun searchPath(destination: GridCoordinate): List<GridCoordinate> {
        return search(gameScene.blockers, gridCoordinate, destination) {
            it > NON_BLOCKER
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

    open fun enterTile(gridCoordinate: GridCoordinate) {
        gameScene.objects.putIntoCoordinate(this, gridCoordinate)
        if (callbackOnDestination != null && gridCoordinate == movePath.last()) {
            callbackOnDestination!!()
        }
    }

    open fun leaveTile(gridCoordinate: GridCoordinate) {
        gameScene.objects.removeFromCoordinate(this, gridCoordinate)
    }

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

    override fun draw(canvas: CanvasRenderingContext2D) {
        val imageAndBlock = animationSet.getFrame(still, direction)
        canvas.drawImage(
            imageAndBlock.first,
            imageAndBlock.second,
            getImageBlockOnCanvas()
        )
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
