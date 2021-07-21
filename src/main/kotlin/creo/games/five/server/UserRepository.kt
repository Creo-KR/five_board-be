package creo.games.five.server

import creo.games.five.server.entity.User
import org.springframework.data.repository.CrudRepository
import java.util.*

class UserRepository: CrudRepository<User, String> {
    override fun <S : User?> save(p0: S): S {
        TODO("Not yet implemented")
    }

    override fun <S : User?> saveAll(p0: MutableIterable<S>): MutableIterable<S> {
        TODO("Not yet implemented")
    }

    override fun findById(p0: String): Optional<User> {
        TODO("Not yet implemented")
    }

    override fun existsById(p0: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableIterable<User> {
        TODO("Not yet implemented")
    }

    override fun findAllById(p0: MutableIterable<String>): MutableIterable<User> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun deleteById(p0: String) {
        TODO("Not yet implemented")
    }

    override fun delete(p0: User) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(p0: MutableIterable<String>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(p0: MutableIterable<User>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}