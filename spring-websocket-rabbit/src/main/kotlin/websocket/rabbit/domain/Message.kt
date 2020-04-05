package websocket.rabbit.domain

import com.fasterxml.jackson.annotation.JsonCreator
import java.io.Serializable
import java.time.LocalDateTime


/**
 * @author lincoln.pires
 */

data class InputMessage @JsonCreator constructor (
        var customerId: String,
        var message: String
): Serializable

data class OutputMessage(
        val customerId: String,
        val message: String,
        val dateTime: LocalDateTime
) : Serializable