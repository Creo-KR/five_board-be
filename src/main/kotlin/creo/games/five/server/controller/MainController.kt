package creo.games.five.server.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MainController {
    @RequestMapping
    @ResponseBody
    fun main(): String = "main"
}
