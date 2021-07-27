package creo.games.five.server.service

import creo.games.five.server.entity.Room
import creo.games.five.server.entity.User
import creo.games.five.server.repository.RoomRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameService(
    private val roomRepository: RoomRepository
) {
    fun createRoom(host: User): Room {
        val room = Room(null, host)
        roomRepository.save(room)
        return room
    }

    fun joinRoom(uuid: UUID, guest: User) {
        var room = roomRepository.findById(uuid).get()
        room.guest = guest
        roomRepository.save(room)
    }

}