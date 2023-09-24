package com.miintto.matstagram.common.security

import com.miintto.matstagram.domain.AuthUser
import io.jsonwebtoken.MalformedJwtException
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
class JwtTokenProviderTests {

    @Autowired
    private lateinit var jwtTokenProvider: JwtTokenProvider

    @Test
    @DisplayName("토큰 발급")
    fun generateToken() {
        val user = getUser()

        jwtTokenProvider.generateToken(user)
    }

    @Test
    @DisplayName("토큰 검증")
    fun validateToken() {
        val user = getUser()
        val token = jwtTokenProvider.generateToken(user)

        assert(jwtTokenProvider.validateToken(token["access"]!!))
        assert(jwtTokenProvider.validateToken(token["refresh"]!!))
    }

    @Test
    @DisplayName("유효하지 않은 토큰 검증")
    fun validateInvalidToken() {
        val token = "asdfasdf"
        assert(!jwtTokenProvider.validateToken(token))
    }

    @Test
    @DisplayName("인증 정보 가져오기")
    fun getAuthentication() {
        val user = getUser()
        val token = jwtTokenProvider.generateToken(user)

        jwtTokenProvider.getAuthentication(token["access"]!!)
    }

    @Test
    @DisplayName("유효하지 않은 토큰으로 인증 정보 가져오기")
    fun getAuthenticationFailed() {
        val token = "asdfasdf"

        assertThrows<MalformedJwtException> {
            jwtTokenProvider.getAuthentication(token)
        }
    }

    private fun getUser(): AuthUser {
        return AuthUser(id = 1, userName = "test-user", userEmail = "test@test.com")
    }
}
