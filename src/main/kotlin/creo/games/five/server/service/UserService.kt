package creo.games.five.server.service

import creo.games.five.server.entity.User
import creo.games.five.server.repository.UserRepository
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private var repository: UserRepository,
    private var passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager
) : UserDetailsService {

    override fun loadUserByUsername(id: String) = repository.findById(id)

    fun login(id: String?, pw: String?): Authentication {
        val context = SecurityContextHolder.getContext()

        val token = UsernamePasswordAuthenticationToken(id, pw)
        return authenticationManager.authenticate(token)
    }

    fun addUser(user: User) {
        user.pw = passwordEncoder.encode(user.pw)
        repository.save(user)
    }

}