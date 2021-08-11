package creo.games.five.server.security

import creo.games.five.server.entity.User
import java.util.*

class AuthenticationToken(
    principal: User,
    var token:String,
    var uuid: UUID? = principal.uuid,
    var id: String? = principal.id,
    var nick: String? = principal.nick
) {

}