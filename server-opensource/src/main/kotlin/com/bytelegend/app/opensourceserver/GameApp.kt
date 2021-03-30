package com.bytelegend.app.opensourceserver

import com.bytelegend.app.servershared.AbstractIndexPageRenderer
import com.bytelegend.app.servershared.AbstractRRBDResourceProvider
import com.bytelegend.app.servershared.JsonMapper
import com.bytelegend.app.servershared.WebSocketMessageDeserializer
import com.bytelegend.app.servershared.dal.SESSION_COOKIE_NAME
import com.bytelegend.app.servershared.install
import com.bytelegend.app.servershared.mock.anonymousPlayer
import com.bytelegend.app.servershared.mock.mockPlayer
import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.entities.SceneInitData
import com.bytelegend.app.shared.entities.States
import com.bytelegend.app.shared.enums.ServerLocation
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.protocol.GET_SCENE_INIT_DATA
import com.bytelegend.app.shared.protocol.MOVE_TO
import com.bytelegend.app.shared.protocol.ONLINE_COUNTER_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.PublishMessage
import com.bytelegend.app.shared.protocol.ReplyMessage
import com.bytelegend.app.shared.protocol.SendMessage
import com.bytelegend.app.shared.protocol.SubscribeUnsubscribeMessage
import com.bytelegend.app.shared.protocol.WebSocketMessage
import com.bytelegend.app.shared.protocol.WebSocketMessageType
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.handler.TextWebSocketHandler
import org.springframework.web.socket.server.HandshakeInterceptor
import java.util.concurrent.ConcurrentHashMap
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@SpringBootApplication
@Controller
class GameApp

@Controller
class IndexController(
    rrbdResourceProvider: AbstractRRBDResourceProvider,
    jsonMapper: JsonMapper
) : AbstractIndexPageRenderer(rrbdResourceProvider, jsonMapper) {
    @GetMapping("/")
    fun index(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        response.writer.apply {
            write(
                renderIndexHtml(
                    "JavaIsland",
                    1,
                    PlayerContext.get(),
                    Locale.EN,
                    "/static",
                    ServerLocation.SEOUL
                )
            )
            flush()
        }
    }

    @GetMapping("/game/login")
    fun login(
        response: HttpServletResponse,
        @RequestParam("redirect")
        redirect: String
    ) {
        response.addCookie(
            Cookie(SESSION_COOKIE_NAME, mockPlayer.id).apply {
                path = "/"
            }
        )
        response.sendRedirect(redirect)
    }

    @GetMapping("/game/logout")
    fun logout(
        response: HttpServletResponse,
        @RequestParam("redirect")
        redirect: String
    ) {
        response.addCookie(
            Cookie(SESSION_COOKIE_NAME, mockPlayer.id).apply {
                path = "/"
                maxAge = 0
            }
        )
        response.sendRedirect(redirect)
    }
}

@Service
class SprintBootRRBDResourceProvider(
    jsonMapper: JsonMapper
) : AbstractRRBDResourceProvider(System.getProperty("local.RRBD"), jsonMapper)

@Service
class JacksonJsonMapper : JsonMapper {
    private val objectMapper = ObjectMapper().apply {
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
        registerModule(KotlinModule())
        install(WebSocketMessage::class.java, WebSocketMessageDeserializer())
    }
    private val yamlMapper = ObjectMapper(YAMLFactory()).apply {
        registerModule(KotlinModule())
    }

    override fun toJson(obj: Any): String = objectMapper.writeValueAsString(obj)
    override fun toPrettyJson(obj: Any) = toJson(obj)
    override fun toUglyJson(obj: Any) = toJson(obj)
    override fun <T> fromJson(string: String, klass: Class<T>): T = objectMapper.readValue(string, klass)
    override fun <T> fromJson(string: String, tr: TypeReference<T>): T = objectMapper.readValue(string, tr)
    override fun <T> fromYaml(string: String, klass: Class<T>): T = yamlMapper.readValue(string, klass)
    override fun <T> fromYaml(string: String, tr: TypeReference<T>): T = yamlMapper.readValue(string, tr)
}

@EnableWebSocket
@Configuration
class Config : WebMvcConfigurer, WebSocketConfigurer {
    @Autowired
    lateinit var jsonMapper: JsonMapper
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/static/**")
            .addResourceLocations("file:${System.getProperty("local.RRBD") ?: throw IllegalStateException("Must set game.resources!")}/")
    }

    private fun readCookie(request: HttpServletRequest) {
        val cookie = request.cookies?.firstOrNull {
            it.name == SESSION_COOKIE_NAME
        }
        if (cookie?.value == mockPlayer.id) {
            PlayerContext.set(mockPlayer)
        } else {
            PlayerContext.set(anonymousPlayer)
        }
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(object : HandlerInterceptor {
            override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
                readCookie(request)
                return true
            }

            override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
                PlayerContext.remove()
            }
        })
    }

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(GameWebSocketServer(jsonMapper), "/game/server")
            .addInterceptors(object : HandshakeInterceptor {
                override fun beforeHandshake(
                    request: ServerHttpRequest,
                    response: ServerHttpResponse,
                    wsHandler: WebSocketHandler,
                    attributes: MutableMap<String, Any>
                ): Boolean {
                    readCookie((request as ServletServerHttpRequest).servletRequest)
                    return true
                }

                override fun afterHandshake(request: ServerHttpRequest, response: ServerHttpResponse, wsHandler: WebSocketHandler, exception: java.lang.Exception?) {
                }
            })
    }
}

object PlayerContext {
    private val threadLocal: ThreadLocal<Player> = ThreadLocal()
    fun set(player: Player) = threadLocal.set(player)
    fun get(): Player = threadLocal.get()
    fun remove() = threadLocal.remove()
}

class GameWebSocketServer(private val jsonMapper: JsonMapper) : TextWebSocketHandler() {
    private val sessions = ConcurrentHashMap<WebSocketSession, Player>()
    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions[session] = PlayerContext.get()
        Thread() {
            Thread.sleep(1000)
            sendMessage(session, PublishMessage(ONLINE_COUNTER_UPDATE_EVENT, sessions.size))
        }.start()
    }

    /**
     * Avoid "The remote endpoint was in state [TEXT_PARTIAL_WRITING] which is an invalid state for called method" error
     */
    @Synchronized
    private fun sendMessage(session: WebSocketSession, messageObj: Any) {
        session.sendMessage(TextMessage(jsonMapper.toJson(messageObj)))
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val wsMessage = jsonMapper.fromJson(message.payload, WebSocketMessage::class.java)
        when (wsMessage.type) {
            WebSocketMessageType.SUBSCRIBE -> onClientSubscribeMessage(session, wsMessage as SubscribeUnsubscribeMessage)
            WebSocketMessageType.UNSUBSCRIBE -> onClientUnsubscribeMessage(session, wsMessage as SubscribeUnsubscribeMessage)
            WebSocketMessageType.SEND -> onClientSendMessage(session, wsMessage as SendMessage)
            else -> throw IllegalStateException("Unsupported type: ${wsMessage.type}")
        }
    }

    private fun onClientSendMessage(session: WebSocketSession, message: SendMessage) {
        try {
            @Suppress("IMPLICIT_CAST_TO_ANY")
            val returnValue = when (message.name) {
                MOVE_TO -> ""
                GET_SCENE_INIT_DATA -> SceneInitData(
                    emptyList(),
                    emptyMap(),
                    States()
                )
                else -> throw IllegalArgumentException("Unsupported message name: ${message.name}")
            }
            val replyMessage = ReplyMessage(WebSocketMessageType.REPLY, message.replyAddress, if (returnValue == Unit) "" else returnValue)
            sendMessage(session, replyMessage)
        } catch (t: Throwable) {
            t.printStackTrace()
            val replyErrorMessage = ReplyMessage(WebSocketMessageType.REPLY_ERROR, message.replyAddress, t.message ?: "")
            sendMessage(session, replyErrorMessage)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onClientUnsubscribeMessage(session: WebSocketSession, subscribeUnsubscribeMessage: SubscribeUnsubscribeMessage) {
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onClientSubscribeMessage(session: WebSocketSession, subscribeUnsubscribeMessage: SubscribeUnsubscribeMessage) {
    }

    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
        exception.printStackTrace()
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessions.remove(session)
    }
}

fun main(args: Array<String>) {
    runApplication<GameApp>(*args)
}
