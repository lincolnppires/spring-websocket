package websoket.redis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringWebsocketRedisApplication

fun main(args: Array<String>) {
	runApplication<SpringWebsocketRedisApplication>(*args)
}
