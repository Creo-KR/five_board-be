package creo.games.five.server.controller

import creo.games.five.server.entity.User
import creo.games.five.server.security.AuthenticationToken
import creo.games.five.server.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.web.bind.annotation.*
import java.util.logging.Logger
import javax.servlet.http.HttpSession

@RestController
@RequestMapping("/user")
class UserController(private val authenticationManager: AuthenticationManager, private val service: UserService) {

    @PostMapping("/login")
    fun login(@RequestBody params: Map<String, String>, session: HttpSession): ResponseEntity<AuthenticationToken> {
        val context = SecurityContextHolder.getContext()

        val id = params["id"];
        val pw = params["pw"];

        val token = UsernamePasswordAuthenticationToken(id, pw)
        val authentication = authenticationManager.authenticate(token)
        context.authentication = authentication
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context)

        return ResponseEntity.ok(AuthenticationToken(id, pw));
    }

    @PostMapping("/signup")
    fun signUp(@RequestBody user: User) {
        service.addUser(user)
    }
}