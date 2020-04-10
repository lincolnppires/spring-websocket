package websocket.client.web

import org.springframework.beans.factory.BeanFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import websocket.client.service.WebSocketClientTest

/**
 * @author lincoln.pires
 */
@RestController
@RequestMapping(value = ["connections"])
class ConnectionController(val beanFactory: BeanFactory) {

    var mutableList = mutableListOf<WebSocketClientTest>()

    @PostMapping("/open")
    @ResponseStatus(value = HttpStatus.OK)
    fun updatePosition(@RequestHeader("customerid") customerId: String) {
        mutableList.add(beanFactory.getBean(WebSocketClientTest::class.java, customerId))
    }



}