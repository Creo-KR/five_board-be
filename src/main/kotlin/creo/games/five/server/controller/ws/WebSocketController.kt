package creo.games.five.server.controller.ws

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class WebSocketController {

    @MessageMapping("/sendTo")
    @SendTo("/topic/connects")
    fun connect(body:String) : String {
        println(body)
        return "PONG"
    }
}