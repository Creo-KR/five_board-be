package creo.games.five.server

import creo.games.five.server.entity.User
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
    val userRepository: UserRepository
) {


    @Test
    fun `addUser`() {
        entityManager.clear()
        val user = User(null, "user1", "ps1")
        entityManager.persist(user)
        entityManager.flush()

        var found = userRepository.findByName("user1")
        assertThat(found).isEqualTo(user)

        found = userRepository.findByName("user2")
        assertThat(found).isNull()
    }

}