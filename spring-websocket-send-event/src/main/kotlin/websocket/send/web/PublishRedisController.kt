package websocket.send.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import websoket.core.domain.InputMessage

/**
 * @author lincoln.pires
 */
@RestController
@RequestMapping(value = ["send"])
class PublishRedisController(private val redisTemplate: RedisTemplate<String, Any>,
                             private val channelTopic: ChannelTopic){

    @Value("\${spring.redis.topic}")
    private val eventTopic: String? = null
    private val logger: Logger = LoggerFactory.getLogger(PublishRedisController::class.java)

    @PostMapping(value = ["/publish-event-redis"])
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun sendEventToBrokerRedis(@RequestBody inputMessage: InputMessage) {
        if (inputMessage.message.isNotEmpty()) {
            logger.info("SEND_EVENT-SIMULATION-PUBSUB-REDIS")
            redisTemplate.convertAndSend(channelTopic.topic, inputMessage)
        } else {
            logger.error("ERROR_SENDING_EVENT_TO_BROKER_REDIS")
        }
    }
}