package creo.games.five.server.security

import io.jsonwebtoken.*
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider {

    private val jwtSecret = "testSecretKey"
    private val jwtExpirationMs = 86400000

    fun generateJwtToken(authentication: Authentication): String {
        val id = authentication.name

        val now = Date()
        val expiration = Date(now.time + jwtExpirationMs)

        return Jwts.builder()
            .setHeaderParam("typ","JWT")
            .setSubject(id)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun getIdFromJwtToken(token: String): String =
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).body.subject;

    fun validateJwtToken(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJwt(token)
            return true
        } catch (e: SignatureException) {
            //logger.error("Invalid JWT signature: {}", e.message);
        } catch (e: MalformedJwtException) {
            //logger.error("Invalid JWT token: {}", e.message);
        } catch (e: ExpiredJwtException) {
            //logger.error("JWT token is expired: {}", e.message);
        } catch (e: UnsupportedJwtException) {
            e.printStackTrace()
            //logger.error("JWT token is unsupported: {}", e.message);
        } catch (e: IllegalArgumentException) {
            //logger.error("JWT claims string is empty: {}", e.message);
        }
        return false;


    }
}