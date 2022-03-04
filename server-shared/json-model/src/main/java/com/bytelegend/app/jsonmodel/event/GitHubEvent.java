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
package com.bytelegend.app.jsonmodel.event;

import com.bytelegend.app.jsonmodel.generated.event.CheckRunGitHubEvent;
import com.bytelegend.app.jsonmodel.generated.event.CommitStatusGitHubEvent;
import com.bytelegend.app.jsonmodel.generated.event.PullRequestGitHubEvent;
import com.bytelegend.app.jsonmodel.generated.event.StarGitHubEvent;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

public interface GitHubEvent {
    BiMap<String, Class<? extends GitHubEvent>> EVENT_TYPES = ImmutableBiMap.<String, Class<? extends GitHubEvent>>builder()
            .put("status", CommitStatusGitHubEvent.class)
            .put("check_run", CheckRunGitHubEvent.class)
            .put("pull_request", PullRequestGitHubEvent.class)
            .put("star", StarGitHubEvent.class)
            .build();

    default String getEventType() {
        return EVENT_TYPES.inverse().get(getClass());
    }

    static Class<? extends GitHubEvent> getEventClassByType(String eventType) {
        return EVENT_TYPES.get(eventType);
    }
}
