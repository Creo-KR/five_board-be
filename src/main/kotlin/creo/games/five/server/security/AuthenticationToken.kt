package creo.games.five.server.security

import creo.games.five.server.entity.User
import java.util.*

class AuthenticationToken(
    var token: String? = null,
    var uuid: UUID? = null,
    var id: String? = null,
    var nick: String? = null
) {
    constructor(principal: User, token: String) : this() {
        uuid = principal.uuid
        id = principal.id
        nick = principal.nick
        this.token = token
    }

}