package com.miintto.matstagram.common.security

import com.miintto.matstagram.api.user.domain.AuthUser
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtTokenProvider {

    @Value("\${jwt.secret}")
    private lateinit var secretKey: String

    private val accessExpirationInterval = 60 * 60 * 1000L  // 1시간

    private val refreshExpirationInterval = 90 * 24 * 60 * 60 * 1000L  // 90일

    private fun getSecretKey() = Keys.hmacShaKeyFor(secretKey.toByteArray())

    private fun buildToken(claims: Claims, interval: Long): String {
        val now = Date()
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + interval))
            .signWith(getSecretKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    private fun generateAccessToken(user: AuthUser): String {
        val claims = Jwts.claims().setSubject(user.id.toString())
        claims["userName"] = user.userName
        claims["permission"] = user.userPermission
        claims["type"] = TokenType.ACCESS.value
        return buildToken(claims, accessExpirationInterval)
    }

    private fun generateRefreshToken(user: AuthUser): String {
        val claims = Jwts.claims().setSubject(user.id.toString())
        claims["active"] = user.isActive
        claims["type"] = TokenType.REFRESH.value
        return buildToken(claims, refreshExpirationInterval)
    }

    fun generateToken(user: AuthUser): Map<String, String> {
        return mapOf(
            "access" to generateAccessToken(user),
            "refresh" to generateRefreshToken(user),
        )
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parse(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAuthentication(token: String): Authentication {
        val claims = Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).body
        val userDetails = User.builder()
            .username(claims.subject)
            .password("")
            .roles(claims["permission"].toString())
            .build()
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }
}
