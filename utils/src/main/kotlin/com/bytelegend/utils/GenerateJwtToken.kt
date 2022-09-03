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
package com.bytelegend.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.bouncycastle.util.io.pem.PemObject
import org.bouncycastle.util.io.pem.PemReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers.ofString
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.interfaces.RSAPrivateKey
import java.security.spec.EncodedKeySpec
import java.security.spec.PKCS8EncodedKeySpec
import java.time.Instant
import java.util.Date

// openssl pkcs8 -topk8 -inform PEM -outform PEM -in server/app/src/main/resources/gradlelegend.2020-10-31.private-key.pem -out server/app/src/main/resources/gradlelegend.2020-10-31.private-key.pkcs8.pem -nocrypt
val privateKeyPath = File("server/app/src/main/resources/gradlelegend.2020-10-31.private-key.pkcs8.pem")
const val appId = "84439"

val privateKey = readPrivateKeyFromFile(privateKeyPath, "RSA") as RSAPrivateKey
val algorithm = Algorithm.RSA256(null, privateKey)

// https://developer.github.com/apps/building-github-apps/authenticating-with-github-apps/#authenticating-as-a-github-app
fun signJwt() =
    JWT.create()
        .withIssuer(appId)
        .withIssuedAt(Date())
        .withExpiresAt(Date.from(Instant.now().plusSeconds(600)))
        .sign(algorithm)

fun main() {
    authenticateAsInstallation()
}

fun authenticateAsGitHubApp() {
    val httpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .header("Authorization", "Bearer ${signJwt()}")
        .header("Accept", "application/vnd.github.machine-man-preview+json")
        .uri(URI.create("https://api.github.com/app"))
        .GET()
        .build()
    val response = httpClient.send(request, ofString())
    println(response.body())
}

fun authenticateAsInstallation() {
    val installationId = 12345726
    val httpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .header("Authorization", "Bearer ${signJwt()}")
        .header("Accept", "application/vnd.github.machine-man-preview+json")
        .uri(URI.create("https://api.github.com/app/installations/$installationId/access_tokens"))
        .POST(HttpRequest.BodyPublishers.ofString(""))
        .build()
    val response = httpClient.send(request, ofString())
    println(response.body())
}

private fun getPrivateKey(keyBytes: ByteArray, algorithm: String): PrivateKey {
    val kf: KeyFactory = KeyFactory.getInstance(algorithm)
    val keySpec: EncodedKeySpec = PKCS8EncodedKeySpec(keyBytes)
    return kf.generatePrivate(keySpec)
}

fun readPrivateKeyFromFile(filepath: File, algorithm: String): PrivateKey {
    val bytes: ByteArray = parsePEMFile(filepath)
    return getPrivateKey(bytes, algorithm)
}

private fun parsePEMFile(pemFile: File): ByteArray {
    if (!pemFile.isFile || !pemFile.exists()) {
        throw FileNotFoundException(String.format("The file '%s' doesn't exist.", pemFile.absolutePath))
    }
    return PemReader(FileReader(pemFile)).use {
        val pemObject: PemObject = it.readPemObject()
        pemObject.content
    }
}
