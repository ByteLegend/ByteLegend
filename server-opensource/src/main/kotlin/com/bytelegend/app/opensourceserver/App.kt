package com.bytelegend.app.opensourceserver

import com.bytelegend.app.serverapi.mock.anonymousPlayer
import com.bytelegend.app.serverapi.mock.mockPlayer
import com.bytelegend.app.serverapi.session.sessionCookieName
import com.bytelegend.app.shared.Player
import com.bytelegend.app.shared.ServerLocation
import com.bytelegend.app.shared.ServerSideData
import com.bytelegend.app.shared.i18n.Locale
import kotlinx.serialization.json.Json
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@SpringBootApplication
@Controller
class App {
    @GetMapping("/")
    fun index(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        var indexHtml = javaClass.getResource("/index.html").readText()
        val model = mutableMapOf<String, String>()
        val locale = Locale.EN
        val RRBD = "/static"
        model["{RRBD}"] = RRBD
        model["{LANG}"] = locale.language.code
        model["{TITLE}"] = "Enjoy programming"
        model["{serverSideData}"] = Json.encodeToString(
            ServerSideData.serializer(),
            ServerSideData(
                ServerLocation.SEOUL,
                RRBD,
                locale,
                "Enjoy programming",
                "JavaIsland",
                PlayerContext.get(),
                null
            )
        )

        model.forEach { (key, value) ->
            indexHtml = indexHtml.replace(key, value)
        }
        response.writer.apply {
            write(indexHtml)
            flush()
        }
    }

    @GetMapping("/login")
    fun login(
        response: HttpServletResponse,
        @RequestParam("redirect")
        redirect: String
    ) {
        if (PlayerContext.get().isAnonymous) {
            response.addCookie(Cookie(sessionCookieName, mockPlayer.id))
        }
        response.sendRedirect(redirect)
    }
}

@Configuration
class Config : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/static/**")
            .addResourceLocations("file:${System.getProperty("game.resources") ?: throw IllegalStateException("Must set game.resources!")}/")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(object : HandlerInterceptor {
            override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
                val cookie = request.cookies?.firstOrNull {
                    it.name == sessionCookieName
                }
                if (cookie?.value == mockPlayer.id) {
                    PlayerContext.set(mockPlayer)
                } else {
                    PlayerContext.set(anonymousPlayer)
                }
                return true
            }

            override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
                PlayerContext.remove()
            }
        })
    }
}

object PlayerContext {
    private val threadLocal: ThreadLocal<Player> = ThreadLocal()
    fun set(player: Player) = threadLocal.set(player)
    fun get() = threadLocal.get()
    fun remove() = threadLocal.remove()
}

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
