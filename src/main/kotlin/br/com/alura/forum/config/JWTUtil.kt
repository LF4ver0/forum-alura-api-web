package br.com.alura.forum.config

import br.com.alura.forum.model.Role
import br.com.alura.forum.service.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.Jwts.SIG.HS256
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JWTUtil(
    private val usuarioService: UsuarioService

){

    private val expiration: Long = 60000

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(username: String, authorities: List<Role>): String? {
        val key = Keys.hmacShaKeyFor(secret.toByteArray())

        return Jwts.builder()
            .subject(username)
            .claim("role", authorities)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(key, HS256)
            .compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.toByteArray()))
                .build()
                .parseSignedClaims(jwt)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAuthentication(jwt: String?) : Authentication {
        val username = Jwts.parser()
                            .verifyWith(Keys.hmacShaKeyFor(secret.toByteArray()))
                            .build()
                            .parseSignedClaims(jwt).payload.subject
        val user = usuarioService.loadUserByUsername(username)

        return UsernamePasswordAuthenticationToken(username, null, user?.authorities)
    }
}
