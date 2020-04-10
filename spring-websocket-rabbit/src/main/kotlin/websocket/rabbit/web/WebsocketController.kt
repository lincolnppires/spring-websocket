package websocket.rabbit.web

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.stereotype.Controller
import websoket.core.domain.InputMessage
import websoket.core.domain.OutputMessage
import java.time.LocalDateTime


/**
 * @author lincoln.pires
 */
@Controller
class WebsocketController {

    @MessageMapping("/")
    @SendTo("/topic/sendMessage")
    @Throws(Exception::class)
    fun broadcast(inputMessage: InputMessage): OutputMessage? {
        return OutputMessage(customerId = inputMessage.customerId,
                message = inputMessage.message,
                dateTime = LocalDateTime.now())
    }

    @MessageMapping("/user")
    @SendToUser("/queue/sendMessage")
    @Throws(Exception::class)
    fun sendUser(inputMessage: InputMessage): OutputMessage? {
        return OutputMessage(customerId = inputMessage.customerId,
                message = inputMessage.message,
                dateTime = LocalDateTime.now())
    }
}