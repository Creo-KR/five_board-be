package creo.games.five.server.controller.api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController {
    @RequestMapping("")
    fun main(): String = "api";
}