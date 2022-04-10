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
package com.bytelegend.app.shared

const val INVITER_ID_STATE = "INVITER_ID"

// How many coins the inviter and invitee can get from an invitation code
const val COIN_REWARD_PER_CODE = 500

// Max coins the inviter can get from an invitation code
const val MAX_COIN_REWARD_PER_CODE = 5000

data class InvitationInformation(
    // The inviterId. null means it's not invited by anyone.
    val inviterId: String?,
    // The invitation code to give to other people.
    val invitationCode: String?,
    // How many coins rewarded by the invitation code
    val rewardedCoin: Int
)
