package websocket.rabbit.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import websocket.rabbit.domain.InputMessage

/**
 * @author lincoln.pires
 */

@RestController
@RequestMapping(value = ["query"])
class PublishQueryController(private val rabbitTemplate: RabbitTemplate) {

    @Value("\${websocket.exchange.messages}")
    private val eventExchange: String? = null
    private val logger: Logger = LoggerFactory.getLogger(PublishQueryController::class.java)

    @PostMapping(value = ["/publish-event-rabbit"])
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun sendEventToBroker(@RequestBody inputMessage: InputMessage) {
        if(inputMessage.message.isNotEmpty()) {
            logger.info("SIMULATION_EVENT_${inputMessage.toString()}")
            rabbitTemplate.convertAndSend(eventExchange!!,"", inputMessage)
        } else{
            logger.error("ERROR_MESSAGE_WITHOUT_CONTENT")
        }

    }
}