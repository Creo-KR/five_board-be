package creo.games.five.server

import creo.games.five.server.entity.Room
import creo.games.five.server.entity.User
import creo.games.five.server.repository.RoomRepository
import creo.games.five.server.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DataTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository,
    val roomRepository: RoomRepository
) {


    @Test
    fun `addUser`() {
        entityManager.clear()
        val user = User(null, "test1", "pw1", "TESTNICK")
        entityManager.persist(user)
        entityManager.flush()

        var found = userRepository.findById("test1")
        assertThat(found).isEqualTo(user)
    }

    @Test
    fun `addRoom`() {
        addUser()

        val user = userRepository.findById("test1")
        val room = Room(null, user)
        entityManager.persist(room)
        entityManager.flush()

        var found = roomRepository.findLatestByHost(user)
        assertThat(found).isEqualTo(room)
    }

}