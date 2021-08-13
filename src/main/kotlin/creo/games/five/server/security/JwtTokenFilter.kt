package creo.games.five.server.security

import creo.games.five.server.service.UserService
import org.aspectj.util.LangUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtTokenFilter constructor(
    private val jwtProvider: JwtProvider,
    @Lazy private val userService: UserService
) : OncePerRequestFilter() {


    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        val header = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (LangUtil.isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response)
            return
        }

        val token = header.split(" ")[1].trim()
        if (!jwtProvider.validateJwtToken(token)) {
            chain.doFilter(request, response)
            return
        }

        val id = jwtProvider.getIdFromJwtToken(token)
        val user = userService.loadUserByUsername(id)

        val authToken = UsernamePasswordAuthenticationToken(user, null, user.authorities)
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

        SecurityContextHolder.getContext().authentication = authToken
        chain.doFilter(request, response)
    }


}