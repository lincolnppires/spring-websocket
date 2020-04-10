package websocket.send.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import websoket.core.domain.InputMessage

/**
 * @author lincoln.pires
 */
@RestController
@RequestMapping(value = ["send"])
class PublishRabbitController(private val rabbitTemplate: RabbitTemplate) {

    @Value("\${websocket.exchange.messages}")
    private val eventExchange: String? = null
    private val logger: Logger = LoggerFactory.getLogger(PublishRabbitController::class.java)

    @PostMapping(value = ["/publish-event"])
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun sendEventToBroker(@RequestBody inputMessage: InputMessage) {
        if(inputMessage.message.isNotEmpty()) {
            logger.info("SEND_EVENT-SIMULATION-FANOUT-RABBIT")
            rabbitTemplate.convertAndSend(eventExchange!!,"", inputMessage)
        } else{
            logger.error("ERROR_SENDING_EVENT_TO_BROKER_RABBIT")
        }

    }
}