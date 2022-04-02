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

package com.bytelegend.app.testfixtures

import com.bytelegend.app.backend.dal.ServerCoordinator
import com.bytelegend.app.shared.enums.ServerLocation

class MockServerCoordinator(override val currentId: Int, override val currentLocation: ServerLocation) : ServerCoordinator {
    override suspend fun getOnlineServerIds(): Set<Int> {
        TODO("Not yet implemented")
    }
}
