package websocket.send.configuration

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author lincoln.pires
 */
@Configuration
class AmqpConfig {

    @Bean
    fun producerJackson2MessageConverter(): Jackson2JsonMessageConverter? {
        return Jackson2JsonMessageConverter()
    }
}