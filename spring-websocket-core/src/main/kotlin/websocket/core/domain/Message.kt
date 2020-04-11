package websoket.core.domain

import java.io.Serializable
import java.time.LocalDateTime


/**
 * @author lincoln.pires
 */
data class InputMessage (
        val customerId: String,
        val message: String
): Serializable

data class OutputMessage(
        val customerId: String,
        val message: String,
        val dateTime: LocalDateTime
) : Serializable