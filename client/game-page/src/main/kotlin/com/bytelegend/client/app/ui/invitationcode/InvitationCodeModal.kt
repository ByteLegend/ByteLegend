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

import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.client.ui.bootstrap.BootstrapFormControl
import com.bytelegend.app.client.ui.bootstrap.BootstrapInputGroup
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalTitle
import com.bytelegend.client.app.ui.GameProps
import react.RBuilder
import react.RComponent
import react.State
import react.dom.p

private const val INVITATION_CODE_REWARDED = "INVITATION_CODE_REWARDED"

class InvitationCodeModal : RComponent<GameProps, State>() {
    override fun RBuilder.render() {
        BootstrapModalHeader {
            attrs.closeButton = true
            BootstrapModalTitle {
                +props.game.i("InvitationCode")
            }
        }
        BootstrapModalBody {
            if (props.game.heroPlayer.isAnonymous) {
                +""
            } else if (props.game.heroPlayer.states.containsKey(INVITATION_CODE_REWARDED)) {
                p {
                    +"You have already got the reward from @xxx's invitation code"
                    +"You invitation code is:"
                    +"XXXXXXX"
                    +"Feel free to share this code in any forums, SNS."
                }
            } else {
                p {
                    +props.game.i("InvitationCodeBoxDescription")
                }
                BootstrapInputGroup {
                    BootstrapFormControl {
                    }
                    BootstrapButton {
                        +"OK"
                    }
                }
            }
        }
    }
}
