package com.miintto.matstagram.auth.service

import com.miintto.matstagram.api.auth.dto.RegisterInfo
import com.miintto.matstagram.api.auth.service.RegisterService
import com.miintto.matstagram.api.user.domain.AuthUser
import com.miintto.matstagram.api.user.domain.repository.AuthUserRepository
import com.miintto.matstagram.common.exception.ApiException
import com.miintto.matstagram.common.security.JwtTokenProvider
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
class RegisterServiceTests {

    @MockBean
    private lateinit var authUserRepository: AuthUserRepository

    @MockBean
    private lateinit var jwtTokenProvider: JwtTokenProvider

    @MockBean
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var registerService: RegisterService

    @Test
    @DisplayName("회원가입 성공")
    fun testServiceRun() {
        val user = getUser()
        val registerInfo = getRegisterInfo()

        given(authUserRepository.existsByUserName(registerInfo.userName)).willReturn(false)
        given(authUserRepository.existsByUserEmail(registerInfo.userEmail)).willReturn(false)
        given(passwordEncoder.encode(registerInfo.password)).willReturn("")
        given(authUserRepository.save(any(AuthUser::class.java))).willReturn(user)
        given(jwtTokenProvider.generateToken(user)).willReturn(mapOf())

        registerService.run(registerInfo)

        verify(authUserRepository).existsByUserName(registerInfo.userName)
        verify(authUserRepository).existsByUserEmail(registerInfo.userEmail)
        verify(passwordEncoder).encode(registerInfo.password)
        verify(authUserRepository).save(any(AuthUser::class.java))
        verify(jwtTokenProvider).generateToken(user)
    }

    @Test
    @DisplayName("회원가입 실패 - 존재하는 닉네임")
    fun testInvalidUserName() {
        val registerInfo = getRegisterInfo()

        given(authUserRepository.existsByUserName(registerInfo.userName)).willReturn(true)

        assertThrows<ApiException> {
            registerService.run(registerInfo)
        }
    }

    @Test
    @DisplayName("회원가입 실패 - 존재하는 이메일")
    fun testInvalidUserEmail() {
        val registerInfo = getRegisterInfo()

        given(authUserRepository.existsByUserName(registerInfo.userName)).willReturn(false)
        given(authUserRepository.existsByUserEmail(registerInfo.userEmail)).willReturn(true)

        assertThrows<ApiException> {
            registerService.run(registerInfo)
        }
    }

    @Test
    @DisplayName("회원가입 실패 - 비밀번호 불일치")
    fun testPasswordDoesNotMatched() {
        val registerInfo = RegisterInfo(
            userName = "test-user",
            userEmail = "test@test.com",
            password = "password",
            passwordAgain = "other-password"
        )

        given(authUserRepository.existsByUserName(registerInfo.userName)).willReturn(false)
        given(authUserRepository.existsByUserEmail(registerInfo.userEmail)).willReturn(false)

        assertThrows<ApiException> {
            registerService.run(registerInfo)
        }
    }

    private fun getUser(): AuthUser {
        return AuthUser(userName = "test-user", userEmail = "test@test.com", password = "encrypted-string")
    }

    private fun getRegisterInfo(): RegisterInfo {
        return RegisterInfo(
            userName = "test-user",
            userEmail = "test@test.com",
            password = "password",
            passwordAgain = "password"
        )
    }
}
