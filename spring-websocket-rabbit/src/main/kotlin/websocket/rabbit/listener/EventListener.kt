package websocket.rabbit.listener

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.ExchangeTypes
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Component
import websocket.rabbit.WebSocketEventListener
import websoket.core.domain.InputMessage


/**
 * @author lincoln.pires
 */
@Component
class EventListener(val messagingTemplate: SimpMessageSendingOperations, val objectMapper: ObjectMapper) {

    private val logger: Logger = LoggerFactory.getLogger(WebSocketEventListener::class.java)

    @RabbitListener(bindings = [QueueBinding(value = Queue("\${websocket.queue.event}", durable = "false"),
            exchange = Exchange(name = "\${websocket.exchange.messages}", type = ExchangeTypes.FANOUT))])
    fun eventListener(message: String) {
        val inputMessage = objectMapper.readValue(message, InputMessage::class.java)

         inputMessage.customerId.isNotEmpty().let {
            logger.info("SUCCESSFULLY_CAPTURED_MESSAGE_${message}")
            messagingTemplate.convertAndSendToUser(inputMessage.customerId, "/queue/sendMessage", message.toString())
        }
    }
}