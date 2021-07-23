package creo.games.five.server.service

import creo.games.five.server.entity.User
import creo.games.five.server.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(private var repository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(name: String) = repository.findByName(name)

    fun addUser(user : User) {
        repository.save(user)
    }

}