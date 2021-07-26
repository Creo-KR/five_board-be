package creo.games.five.server.repository

import creo.games.five.server.entity.Room
import creo.games.five.server.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoomRepository : CrudRepository<Room, Long> {
    fun findLatestByHost(host: User): Room
}