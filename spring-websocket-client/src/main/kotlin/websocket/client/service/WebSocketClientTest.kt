package websocket.client.service

import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.simp.stomp.StompSessionHandler
import org.springframework.web.socket.WebSocketHttpHeaders
import org.springframework.web.socket.client.WebSocketClient
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.messaging.WebSocketStompClient

/**
 * @author lincoln.pires
 */
class WebSocketClientTest(customerId: String) {

    val client: WebSocketClient = StandardWebSocketClient()
    val stompClient = WebSocketStompClient(client)

    init {
        stompClient.messageConverter = MappingJackson2MessageConverter()
        val sessionHandler: StompSessionHandler = ClientStompSessionHandler()
        val handshakeHeaders = WebSocketHttpHeaders()
        handshakeHeaders.add("customerid", customerId)
        println(stompClient.connect("ws://localhost:8282/event-websocket",handshakeHeaders, sessionHandler).get().sessionId)
    }
}