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
package com.bytelegend.app.client.api

import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import kotlinx.browser.window

fun closeMissionModalEvent(id: String) = "close.mission.modal.$id"
fun openMissionModalEvent(id: String) = "open.mission.modal.$id"

fun DynamicSprite.configureBookSprite() {
    onClickFunction = {
        animation = FramePlayingAnimation(arrayOf(AnimationFrame(1, 500)), false)
        window.setTimeout({
            gameScene.gameRuntime.eventBus.emit(openMissionModalEvent(id), null)
        }, 500)
    }

    val onMissionModalClose: EventListener<Unit> = {
        window.setTimeout({ animation = Static }, 500)
    }

    gameScene.gameRuntime.eventBus.on(closeMissionModalEvent(id), onMissionModalClose)

    onCloseFunction = {
        gameScene.gameRuntime.eventBus.remove(closeMissionModalEvent(id), onMissionModalClose)
    }
}

fun GameMapDynamicSprite.animationWithFixedInterval(ms: Int): FramePlayingAnimation {
    val size = frames[0][0].size
    val array = emptyArray<AnimationFrame>()
    repeat(size) {
        array[it] = (AnimationFrame(it, ms))
    }
    return FramePlayingAnimation(array)
}
