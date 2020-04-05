package websoket.redis.domain

import com.fasterxml.jackson.annotation.JsonCreator
import java.io.Serializable
import java.time.LocalDateTime


/**
 * @author lincoln.pires
 */

data class InputMessage @JsonCreator constructor (
        val customerId: String,
        val message: String
): Serializable

data class OutputMessage(
        val customerId: String,
        val message: String,
        val dateTime: LocalDateTime
) : Serializable