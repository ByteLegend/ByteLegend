/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.bytelegend.app.client.api.AnimationBuilder
import com.bytelegend.app.client.api.GameMission
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Invisible
import com.bytelegend.app.client.misc.uuid
import com.bytelegend.app.client.utils.animationWithFixedInterval
import com.bytelegend.app.client.utils.refreshTeslaCoilAnimation
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.objects.CoordinateAware
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import kotlinx.browser.window
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.sqrt

fun GameScene.teslaCoilAttackAnimation(startMission: GameMission, endObject: CoordinateAware, eshock: Boolean = true) {
    scripts("thunder-current-${uuid()}") {
        val sprite = objects.getById<GameMapDynamicSprite>("TeslaCoilAttack")
        startMission.mapDynamicSprite = sprite
        startMission.animation = sprite.animationWithFixedInterval(100, 0, -1, false)
        val frameNumber = sprite.frames[0][0].size

        val attackAnimationDuration = frameNumber * 100L
        val attackAnimation = AnimationBuilder().apply {
            animationId = "ThunderCurrent"
            audioId = "ThunderCurrentAudio"
            frameDurationMs = 100
            initDelayMs = attackAnimationDuration
            onStart = {
                startMission.mapDynamicSprite = objects.getById("TeslaCoil")
                startMission.animation = startMission.mapDynamicSprite.animationWithFixedInterval(200, 2, 11, true)
            }
            onDraw = { canvas, frameIndex ->
                val start = startMission.pixelCoordinate + PixelSize(16, 0)
                val end = endObject.pixelCoordinate + PixelSize(16, 16)

                val startPointOnCanvas = start - canvasState.getCanvasCoordinateInMap()
                val endPointOnCanvas = end - canvasState.getCanvasCoordinateInMap()

                canvas.translate(startPointOnCanvas.x.toDouble(), startPointOnCanvas.y.toDouble())
                // TODO merge them
                if (endPointOnCanvas.y < startPointOnCanvas.y) {
                    canvas.rotate(PI - atan(1.0 * (endPointOnCanvas.x - startPointOnCanvas.x) / (endPointOnCanvas.y - startPointOnCanvas.y)))
                } else {
                    canvas.rotate(-atan(1.0 * (endPointOnCanvas.x - startPointOnCanvas.x) / (endPointOnCanvas.y - startPointOnCanvas.y)))
                }

                val distance =
                    sqrt(1.0 * (endPointOnCanvas.y - startPointOnCanvas.y) * (endPointOnCanvas.y - startPointOnCanvas.y) + (endPointOnCanvas.x - startPointOnCanvas.x) * (endPointOnCanvas.x - startPointOnCanvas.x))
                val frameSize = gameMapAnimation.frameSize
                canvas.drawImage(
                    image.htmlElement, frameSize.width.toDouble() * frameIndex, 0.0, frameSize.width.toDouble(), frameSize.height.toDouble(),
                    -45.0, 0.0, frameSize.width.toDouble(), distance + 32
                )
            }
        }

        if (eshock) {
            animation(attackAnimation, AnimationBuilder().apply {
                animationId = "e-shock"
                frameDurationMs = 100
                initDelayMs = attackAnimationDuration + 300
                onDraw = { canvas, frameIndex ->
                    val frameSize = gameMapAnimation.frameSize
                    val end = endObject.pixelCoordinate - PixelSize(8, 8)
                    val endPointOnCanvas = end - canvasState.getCanvasCoordinateInMap()
                    canvas.drawImage(
                        image.htmlElement, frameSize.width.toDouble() * frameIndex, 0.0, frameSize.width.toDouble(), frameSize.height.toDouble(),
                        endPointOnCanvas.x.toDouble(), endPointOnCanvas.y.toDouble(), 48.0, 48.0
                    )
                }
            })
        } else {
            animation(attackAnimation)
        }
    }
}

fun GameScene.killEvil(mission: GameMission) {
    scripts("kill-evil-${uuid()}") {
        animation {
            animationId = "KillEvil"
            audioId = "SwingWeapon"
            frameDurationMs = 200
            initDelayMs = 100
            onDraw = { canvas, frameIndex ->
                val missionPointOnCanvas = mission.pixelCoordinate - canvasState.getCanvasCoordinateInMap()
                val frameSize = gameMapAnimation.frameSize
                canvas.drawImage(
                    image.htmlElement,
                    frameSize.width.toDouble() * frameIndex, 0.0, frameSize.width.toDouble(), frameSize.height.toDouble(),
                    missionPointOnCanvas.x.toDouble() - 32, missionPointOnCanvas.y.toDouble() - 32, 96.0, 96.0
                )
            }
        }
    }
}

fun GameScene.ironSwordAttack(missionTower: GameMission) {
    scripts("iron-sword-attack-${uuid()}") {
        animation {
            animationId = "IronSwordAttack"
            audioId = "SwingWeapon"
            frameDurationMs = 200
            initDelayMs = 100
            onDraw = { canvas, frameIndex ->
                val missionPointOnCanvas = missionTower.pixelCoordinate - canvasState.getCanvasCoordinateInMap()
                val frameSize = gameMapAnimation.frameSize
                canvas.drawImage(
                    image.htmlElement,
                    frameSize.width.toDouble() * frameIndex, 0.0, frameSize.width.toDouble(), frameSize.height.toDouble(),
                    missionPointOnCanvas.x.toDouble() - 32, missionPointOnCanvas.y.toDouble() - 32, 96.0, 96.0
                )
            }
        }
        runSuspend {
            refreshTeslaCoilAnimation(missionTower)
        }
    }
}

fun GameScene.silverSwordAttack(missionTower: GameMission) {
    val attackMs = 2000
    window.setTimeout({
        missionTower.animation = Invisible
    }, attackMs)
    scripts("silver-sword-attack-${uuid()}") {
        animation(AnimationBuilder().apply {
            animationId = "SilverSwordAttack"
            audioId = "GoldSwordAudio"
            frameDurationMs = 250
            onDraw = { canvas, frameIndex ->
                val missionPointOnCanvas = missionTower.pixelCoordinate - canvasState.getCanvasCoordinateInMap()
                val frameSize = gameMapAnimation.frameSize
                canvas.drawImage(
                    image.htmlElement,
                    frameSize.width.toDouble() * frameIndex, 0.0, frameSize.width.toDouble(), frameSize.height.toDouble(),
                    missionPointOnCanvas.x.toDouble() - 80, missionPointOnCanvas.y.toDouble() - 96, 192.0, 192.0
                )
            }
        })

        runSuspend {
            refreshTeslaCoilAnimation(missionTower)
        }
    }
}

fun GameScene.goldSwordAttack(missionTower: GameMission) {
    val attackMs = 2000
    window.setTimeout({
        missionTower.animation = Invisible
    }, attackMs)
    scripts("gold-sword-attack-${uuid()}") {
        animation(AnimationBuilder().apply {
            animationId = "GoldSwordAttack"
            audioId = "GoldSwordAudio"
            frameDurationMs = 250
            onDraw = { canvas, frameIndex ->
                val missionPointOnCanvas = missionTower.pixelCoordinate - canvasState.getCanvasCoordinateInMap()
                val frameSize = gameMapAnimation.frameSize
                canvas.drawImage(
                    image.htmlElement,
                    frameSize.width.toDouble() * frameIndex, 0.0, frameSize.width.toDouble(), frameSize.height.toDouble(),
                    missionPointOnCanvas.x.toDouble() - 80, missionPointOnCanvas.y.toDouble() - 96, 192.0, 192.0
                )
            }
        }, AnimationBuilder().apply {
            animationId = "TeslaCoilDestroyed"
            audioId = "TeslaCoilExplosion"
            frameDurationMs = 100
            initDelayMs = attackMs.toLong()
            onDraw = { canvas, frameIndex ->
                val missionPointOnCanvas = missionTower.pixelCoordinate - canvasState.getCanvasCoordinateInMap()
                val frameSize = gameMapAnimation.frameSize
                canvas.drawImage(
                    image.htmlElement,
                    frameSize.width.toDouble() * frameIndex, 0.0, frameSize.width.toDouble(), frameSize.height.toDouble(),
                    missionPointOnCanvas.x.toDouble() - 16, missionPointOnCanvas.y.toDouble() - 16, 64.0, 80.0
                )
            }
        })
        runSuspend {
            refreshTeslaCoilAnimation(missionTower)
        }
    }
}
