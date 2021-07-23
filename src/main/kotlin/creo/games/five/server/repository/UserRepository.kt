package creo.games.five.server.repository

import creo.games.five.server.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: CrudRepository<User, UUID> {
    fun findByName(name: String): User
}