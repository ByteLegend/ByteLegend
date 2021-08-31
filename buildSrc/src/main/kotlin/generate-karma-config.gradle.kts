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
import java.net.ServerSocket

// https://karma-runner.github.io/4.0/config/configuration-file.html
// https://karma-runner.github.io/4.0/config/files.html
// Note that "proxy" doesn't mean proxy a path to that directory,
// it means proxy that path to another path, i.e. the path in configs (by default "/base/$pattern").
// we use absolute path to avoid complex static resource searching.
fun createKarmaConfig(configFile: File, staticFiles: List<String>, port: Int) {
    configFile.writeText("""
        config.port = $port
        config.files.push(${staticFiles.joinToString(",")})
        config.proxies = {
            '/static/': '$resourceDir'
        }
        config.client.args.push("--port$port")
    """.trimIndent())
}

fun findRandomOpenPort(): Int = ServerSocket(0).use { socket -> return socket.localPort }

val configDir = project.buildDir.resolve("karmaConfig").apply { mkdirs() }
val resourceDir = project.rootProject.rootDir.resolve("resources").absolutePath

createKarmaConfig(
    configDir.resolve("karma.conf.js"),
    listOf("{pattern: '$resourceDir/**/astar.js', watched: false, included: true, served: true, nocache: true}"),
    findRandomOpenPort())
