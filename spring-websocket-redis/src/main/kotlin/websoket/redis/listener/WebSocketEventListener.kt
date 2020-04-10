package websoket.redis.listener

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.web.socket.messaging.SessionConnectedEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent
import org.springframework.web.socket.messaging.SessionSubscribeEvent
import websoket.core.domain.OutputMessage
import java.time.LocalDateTime

/**
 * @author lincoln.pires
 */
@Configuration
class WebSocketEventListener {

    private val logger: Logger = LoggerFactory.getLogger(WebSocketEventListener::class.java)

    @Autowired
    private val messagingTemplate: SimpMessageSendingOperations? = null

    @EventListener
    fun handleWebSocketConnectListener(event: SessionConnectedEvent?) {
        val headerAccessor = StompHeaderAccessor.wrap(event!!.message)
        val session = headerAccessor.sessionId;
        val user = headerAccessor.user

        if (session != null) {
            val msg = OutputMessage(customerId = user!!.name,
                    message = "NEW_SESSION_${session}_FOR_USER_${user!!.name}",
                    dateTime = LocalDateTime.now()
            )
            logger.info(msg.message)
            messagingTemplate!!.convertAndSend("/topic/sendMessage", msg)
            messagingTemplate!!.convertAndSendToUser(user.name,"/queue/sendMessage", "NEW_SESSION_${session}" +
                    "_FOR_YOUR_USER_${user!!.name}")
        }
    }

    @EventListener
    fun handleWebSocketSybscribe(event: SessionSubscribeEvent){
        val headerAccessor = StompHeaderAccessor.wrap(event!!.message)
        val session = headerAccessor.sessionId;
        val user = headerAccessor.user

        if (session != null) {
            val msg = OutputMessage(customerId = user!!.name,
                    message = "SUBSCRIBE_USER_${user!!.name}_DESTINATION_${headerAccessor.destination.toString()}",
                    dateTime = LocalDateTime.now()
            )
            logger.info(msg.message)
            messagingTemplate!!.convertAndSend("/topic/sendMessage", msg)
        }
    }

    @EventListener
    fun handleWebSocketDisconnectListener(event: SessionDisconnectEvent) {
        val headerAccessor = StompHeaderAccessor.wrap(event.message)
        val session = headerAccessor.sessionId;
        val user = headerAccessor.user
        val command = headerAccessor.command

        if (session != null) {
            val msg = OutputMessage(customerId = user!!.name,
                    message = "END_OF_SESSION_${session}_ASSOCIATED_WITH_USER_${user!!.name}",
                    dateTime = LocalDateTime.now()
            )
            logger.info(msg.message)
            messagingTemplate!!.convertAndSend("/topic/sendMessage", msg)
            messagingTemplate!!.convertAndSendToUser(user.name,"/queue/sendMessage", "bye bye ${user!!.name}")
        }
    }
}