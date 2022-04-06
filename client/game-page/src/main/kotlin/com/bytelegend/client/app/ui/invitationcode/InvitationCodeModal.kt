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

package com.bytelegend.client.app.ui.invitationcode

import com.bytelegend.app.client.api.DynamicSprite
import com.bytelegend.app.client.api.StaticFrame
import com.bytelegend.app.client.ui.bootstrap.BootstrapAlert
import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.client.ui.bootstrap.BootstrapFormControl
import com.bytelegend.app.client.ui.bootstrap.BootstrapInputGroup
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalTitle
import com.bytelegend.client.utils.toInvitationInformation
import com.bytelegend.app.shared.COIN_REWARD_PER_CODE
import com.bytelegend.app.shared.INVITER_ID_STATE
import com.bytelegend.app.shared.InvitationInformation
import com.bytelegend.app.shared.MAX_COIN_REWARD_PER_CODE
import com.bytelegend.client.app.external.codeBlock
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.loadingSpinner
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.app.ui.unsafeDiv
import com.bytelegend.client.app.ui.unsafeSpan
import com.bytelegend.client.app.web.HttpRequestException
import com.bytelegend.client.app.web.getText
import com.bytelegend.client.app.web.post
import kotlinx.js.jso
import kotlinx.browser.document
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.get
import react.Component
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.h4
import react.dom.html.ReactHTML.p

interface InvitationCodeModalState : State {
    var inviterId: String?
    var invitationInformation: InvitationInformation?
    var errorMessage: String?
    var loading: Boolean
}

interface InvitationCodeModalProps : GameProps

/**
 * If the player hasn't opened this box yet, just display an input box;
 * if the player has already opened the box, send an AJAX to render the modal
 * because we need to know how much reward the player got
 */
class InvitationCodeModal(props: InvitationCodeModalProps) : Component<GameProps, InvitationCodeModalState>(props) {
    init {
        state = jso {
            inviterId = props.game.heroPlayer.states[INVITER_ID_STATE]
            invitationInformation = null
            loading = false
        }
    }

    override fun componentDidMount() {
        if (!state.loading && props.game.heroPlayer.states.containsKey(INVITER_ID_STATE)) {
            setState {
                loading = true
            }
            runCatchingHttpException {
                val newInformation = getInvitationInformation()
                setState {
                    loading = false
                    invitationInformation = newInformation
                }
            }
        }
    }

    override fun render() = Fragment.create {
        BootstrapModalHeader {
            closeButton = true
            BootstrapModalTitle {
                +props.game.i("InvitationCode")
            }
        }
        BootstrapModalBody {
            className = "text-center"
            if (props.game.heroPlayer.isAnonymous) {
                h4 {
                    +props.game.i("YouAreNotLoggedIn")
                }
                p {
                    unsafeSpan(props.game.i("ClickHereToLogin"))
                }
            } else if (state.loading) {
                loadingSpinner()
            } else if (props.game.heroPlayer.states.containsKey(INVITER_ID_STATE)) {
                if (state.invitationInformation != null) {
                    unsafeDiv(
                        props.game.i(
                            "YouAreInvitedBy",
                            state.invitationInformation!!.invitationCode!!,
                            COIN_REWARD_PER_CODE.toString(),
                            state.invitationInformation!!.rewardedCoin.toString(),
                            MAX_COIN_REWARD_PER_CODE.toString(),
                            state.invitationInformation?.inviterId?.substringAfter("#")!!
                        )
                    )
                    codeBlock(withLineNumber = false) {
                        lines = listOf(
                            props.game.i(
                                "JoinMeWithInvitationCode",
                                state.invitationInformation!!.invitationCode!!,
                                invitationBoxPoint().toHumanReadableCoordinate().toString(),
                                COIN_REWARD_PER_CODE.toString()
                            )
                        )
                        language = "none"
                    }
                }
            } else {
                p {
                    +props.game.i("InvitationCodeBoxDescription", COIN_REWARD_PER_CODE.toString())
                }
                BootstrapInputGroup {
                    BootstrapFormControl {
                        this.disabled = disabled
                        className = "invitation-code-input"
                    }
                    BootstrapButton {
                        className = "invitation-code-ok-button"
                        +"OK"
                        this.disabled = disabled
                        onClick = {
                            useInvitationCode()
                        }
                    }
                }
            }

            if (state.errorMessage != null) {
                BootstrapAlert {
                    show = true
                    variant = "danger"
                    +state.errorMessage!!
                }
            }
        }
    }

    private fun invitationBoxPoint() = props.game.activeScene.objects.getPointById("InvitationBox-point")

    private fun useInvitationCode() {
        val code = (document.getElementsByClassName("invitation-code-input")[0]!! as HTMLInputElement).value
        if (code.isBlank()) {
            return
        }
        setState {
            loading = true
            errorMessage = null
        }
        runCatchingHttpException {
            val postResult = postInvitationInformation(code)
            if (postResult.inviterId != null) {
                props.game.heroPlayer.states[INVITER_ID_STATE] = postResult.inviterId!!
            }
            // Open the box
            props.game.activeScene.objects.getById<DynamicSprite>("invitation-code-box").animation = StaticFrame(3)
            setState {
                invitationInformation = postResult
                loading = false
            }
        }
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    private fun runCatchingHttpException(block: suspend () -> Unit) {
        GlobalScope.launch {
            try {
                block()
            } catch (e: HttpRequestException) {
                setState {
                    loading = false
                    errorMessage = when (e.statusCode) {
                        400 -> "Invalid input!"
                        404 -> "Invalid invitation code!"
                        409 -> "You are already invited!"
                        else -> e.message
                    }
                }
            }
        }
    }

    private suspend fun postInvitationInformation(code: String): InvitationInformation {
        return toInvitationInformation(JSON.parse(post("/game/api/invitation", JSON.stringify(jso<dynamic> {
            this.invitationCode = code
        }))))
    }

    private suspend fun getInvitationInformation(): InvitationInformation {
        return toInvitationInformation(JSON.parse(getText("/game/api/invitation")))
    }
}
