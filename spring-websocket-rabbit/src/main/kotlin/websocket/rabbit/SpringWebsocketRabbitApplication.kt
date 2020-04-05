package websocket.rabbit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringWebsocketRabbitApplication

fun main(args: Array<String>) {
	runApplication<SpringWebsocketRabbitApplication>(*args)
}
