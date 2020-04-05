package websocket.rabbit.configuration.model

import java.security.Principal

/**
 * @author lincoln.pires
 */
class StompPrincipal(val user: String) : Principal{

    override fun getName(): String {
        return user
    }
}