package websocket.client.service

import com.fasterxml.jackson.annotation.JsonCreator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaders
import org.springframework.messaging.simp.stomp.StompSession
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter
import websoket.core.domain.InputMessage
import java.io.Serializable
import java.lang.reflect.Type

/**
 * @author lincoln.pires
 */
class ClientStompSessionHandler: StompSessionHandlerAdapter() {

    private val logger: Logger = LoggerFactory.getLogger(ClientStompSessionHandler::class.java)

    override fun afterConnected(session: StompSession, connectedHeaders: StompHeaders) {
        logger.info("NEW_SESSION_STABLISHED_${session.sessionId}")
        val subscription =  session.subscribe("/user/queue/sendMessage", this)
        logger.info("SUBSCRIBED_TO_/queue/sendMessage_${subscription.subscriptionId}")
    }

    override fun handleException(session: StompSession, command: StompCommand?, headers: StompHeaders, payload: ByteArray, exception: Throwable) {
        //val msg: String = payload as String
        logger.error("ERROR_NO_SUITABLE_CONVERTER_FOR_PAYLOAD_TYPE")
    }

    override fun getPayloadType(headers: StompHeaders): Type {
        return InputMessage::class.java
    }

    override fun handleFrame(headers: StompHeaders, payload: Any?) {
        val msg: InputMessage = payload as InputMessage
        logger.info("HANDLE_FRAME_PAYLOAD_$msg")
    }


}
