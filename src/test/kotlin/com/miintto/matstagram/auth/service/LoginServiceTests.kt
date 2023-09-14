package com.miintto.matstagram.auth.service

import com.miintto.matstagram.api.auth.dto.LoginInfo
import com.miintto.matstagram.api.auth.service.LoginService
import com.miintto.matstagram.api.user.domain.AuthUser
import com.miintto.matstagram.api.user.domain.repository.AuthUserRepository
import com.miintto.matstagram.common.exception.ApiException
import com.miintto.matstagram.common.security.JwtTokenProvider
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
class LoginServiceTests {

    @MockBean
    private lateinit var authUserRepository: AuthUserRepository

    @MockBean
    private lateinit var jwtTokenProvider: JwtTokenProvider

    @MockBean
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var loginService: LoginService

    @Test
    @DisplayName("로그인 성공")
    fun testServiceRun() {
        val user = getUser()
        val loginInfo = getLoginInfo()

        given(authUserRepository.findByUserEmail(loginInfo.userEmail)).willReturn(user)
        given(passwordEncoder.matches(loginInfo.password, user.password)).willReturn(true)
        given(jwtTokenProvider.generateToken(user)).willReturn(mapOf())

        loginService.run(loginInfo)

        verify(authUserRepository).findByUserEmail(loginInfo.userEmail)
        verify(passwordEncoder).matches(loginInfo.password, user.password)
        verify(jwtTokenProvider).generateToken(user)
    }

    @Test
    @DisplayName("존재하지 않는 사용자")
    fun testUserNotFound() {
        val loginInfo = getLoginInfo()

        given(authUserRepository.findByUserEmail(loginInfo.userEmail)).willReturn(null)

        assertThrows<ApiException> {
            loginService.run(loginInfo)
        }
    }

    @Test
    @DisplayName("비밀번호 불일치")
    fun testNotMatchedPassword() {
        val user = getUser()
        val loginInfo = getLoginInfo()

        given(authUserRepository.findByUserEmail(loginInfo.userEmail)).willReturn(user)
        given(passwordEncoder.matches(loginInfo.password, user.password)).willReturn(false)

        assertThrows<ApiException> {
            loginService.run(loginInfo)
        }

        verify(authUserRepository).findByUserEmail(loginInfo.userEmail)
    }

    private fun getUser(): AuthUser {
        return AuthUser(userName = "test-user", userEmail = "test@test.com", password = "encrypted-string")
    }

    private fun getLoginInfo(): LoginInfo {
        return LoginInfo("test@test.com", "password")
    }
}
