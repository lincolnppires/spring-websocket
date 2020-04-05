package websoket.redis.configuration

import org.springframework.http.server.ServerHttpRequest
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.support.DefaultHandshakeHandler
import websoket.redis.configuration.model.StompPrincipal
import java.security.Principal

/**
 * @author lincoln.pires
 */
class CustomHandshakeHandler: DefaultHandshakeHandler() {

    override fun determineUser(request: ServerHttpRequest, wsHandler: WebSocketHandler, attributes: MutableMap<String, Any>): Principal? {
        val user = request.headers.getFirst("customerid")
        val securityPrincipal = StompPrincipal(user!!)
        return securityPrincipal
    }
}