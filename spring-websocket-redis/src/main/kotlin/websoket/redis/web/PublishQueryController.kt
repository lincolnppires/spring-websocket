package websoket.redis.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import websoket.redis.domain.InputMessage

/**
 * @author lincoln.pires
 */

@RestController
@RequestMapping(value = ["event"])
class PublishQueryController(private val redisTemplate: RedisTemplate<String, Any>, val channelTopic: ChannelTopic
) {

    private val logger: Logger = LoggerFactory.getLogger(PublishQueryController::class.java)

    @PostMapping(value = ["/publish-event-redis"])
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun sendEventToBrokerRedis(@RequestBody inputMessage: InputMessage) {
        if(inputMessage.message.isNotEmpty()) {
            logger.info("envio msg redis - simulacao modulo query")
            redisTemplate.convertAndSend(channelTopic.topic, inputMessage)
        } else{
            logger.error("erro ao envia msg para o redis")
        }

    }
}