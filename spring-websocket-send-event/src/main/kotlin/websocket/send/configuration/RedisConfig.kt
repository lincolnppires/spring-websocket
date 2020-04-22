package websocket.send.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer

/**
 * @author lincoln.pires
 */
@Configuration
class RedisConfig {

    @Value("\${spring.redis.topic}")
    lateinit var redisTopic: String

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        val redisTemplate: RedisTemplate<String, Any> = RedisTemplate()
        redisTemplate.setConnectionFactory(redisConnectionFactory)
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer()
        return redisTemplate
    }

    @Bean
    fun topic(): ChannelTopic = ChannelTopic(redisTopic)
}