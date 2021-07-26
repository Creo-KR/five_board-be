package creo.games.five.server.service

import creo.games.five.server.entity.Room
import creo.games.five.server.entity.User
import creo.games.five.server.repository.RoomRepository
import org.springframework.stereotype.Service

@Service
class GameService(
    private val roomRepository: RoomRepository
) {
    fun createRoom(host: User): Room {
        val room = Room(null, host)
        roomRepository.save(room)
        return room
    }

    fun joinRoom(roomSeq: Long, guest: User) {
        var room = roomRepository.findById(roomSeq).get()
        room.guest = guest
        roomRepository.save(room)
    }

}