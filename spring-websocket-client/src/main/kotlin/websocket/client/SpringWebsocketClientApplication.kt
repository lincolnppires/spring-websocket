package websocket.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringWebsocketClientApplication

fun main(args: Array<String>) {
	runApplication<SpringWebsocketClientApplication>(*args)
}
