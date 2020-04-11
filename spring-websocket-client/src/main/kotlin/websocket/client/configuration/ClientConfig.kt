package websocket.client.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import websocket.client.service.WebSocketClientTest

/**
 * @author lincoln.pires
 */
@Configuration
class ClientConfig {

    @Bean
    @Scope("prototype")
    fun getWebSocketClient(customerId: String): WebSocketClientTest {
        return WebSocketClientTest(customerId)
    }
}