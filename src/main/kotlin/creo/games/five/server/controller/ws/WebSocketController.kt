package creo.games.five.server.controller.ws

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller

@Controller
class WebSocketController(private val webSocket: SimpMessagingTemplate) {

    @MessageMapping("/sendTo")
    @SendTo("/topic/template")
    fun connect(body: String): String {
        println(body)
        webSocket.convertAndSend("/topic/template1", "Test")
        return "PONG"
    }
}