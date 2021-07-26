package creo.games.five.server.controller.api

import creo.games.five.server.entity.Room
import creo.games.five.server.entity.User
import creo.games.five.server.service.GameService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/game")
class GameController(private val service : GameService) {
    @RequestMapping("create")
    fun create(): ResponseEntity<Long> {
        val context = SecurityContextHolder.getContext()
        val authentication = context.authentication

        val room = service.createRoom(authentication.principal as User)
        return ResponseEntity.ok(room.seq)
    }

    @RequestMapping("join/{seq}")
    fun join(@PathVariable seq:Long): ResponseEntity<String> {
        val context = SecurityContextHolder.getContext()
        val authentication = context.authentication

        val room = service.joinRoom(seq, authentication.principal as User)

        return ResponseEntity.ok("")
    }
}