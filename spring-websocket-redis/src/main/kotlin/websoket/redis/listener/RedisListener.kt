package websoket.redis.listener

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Component
import websoket.core.domain.InputMessage

/**
 * @author lincoln.pires
 */
@Component
class RedisListener(val messagingTemplate: SimpMessageSendingOperations,
                    val objectMapper: ObjectMapper) : MessageListener{

    private val logger: Logger = LoggerFactory.getLogger(WebSocketEventListener::class.java)

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val inputMessage = objectMapper.readValue(message.toString(), InputMessage::class.java)
        logger.info("redis listener - msg: ${inputMessage}")
        messagingTemplate!!.convertAndSendToUser(inputMessage.customerId, "/queue/sendMessage", inputMessage.toString())
    }


}