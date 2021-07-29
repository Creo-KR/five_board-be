package creo.games.five.server.controller.ws

import creo.games.five.server.entity.Room
import creo.games.five.server.service.GameService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class WebSocketController(
    private val webSocket: SimpMessagingTemplate,
    private val gameService: GameService
) {

    @MessageMapping("/start")
    @SendTo("/topic/template")
    fun start(body: String): String {
        println(body)
        gameService.startGame(Room())
        //webSocket.convertAndSend("/topic/template1", "Test")
        return "PONG"
    }

    @RequestMapping("/ws/public")
    fun public() {
        webSocket.convertAndSend("/topic/public", "PUBLIC Message")
    }
}