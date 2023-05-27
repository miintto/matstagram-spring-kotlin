package com.miintto.matstagram.api.auth.service

import com.miintto.matstagram.api.auth.dto.RegisterInfo
import com.miintto.matstagram.api.user.domain.AuthUser
import com.miintto.matstagram.api.user.domain.repository.AuthUserRepository
import com.miintto.matstagram.common.exception.APIException
import com.miintto.matstagram.common.response.code.Http4xx
import com.miintto.matstagram.common.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class RegisterService {

    @Autowired
    private lateinit var authUserRepository: AuthUserRepository

    @Autowired
    private lateinit var jwtTokenProvider: JwtTokenProvider

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    private fun checkDuplicateUser(registerInfo: RegisterInfo) {
        if (authUserRepository.existsByUserName(registerInfo.userName)) {
            throw APIException(Http4xx.BAD_REQUEST, "중복된 닉네임입니다.")
        } else if (authUserRepository.existsByUserEmail(registerInfo.userEmail)) {
            throw APIException(Http4xx.BAD_REQUEST, "중복된 이메일입니다.")
        }
    }

    private fun validatePassword(password1: String, password2: String) {
        if (password1 != password2) {
            throw APIException(Http4xx.INVALID_PASSWORD, "비밀번호가 서로 일치하지 않습니다.")
        }
    }

    private fun createUser(registerInfo: RegisterInfo): AuthUser {
        val user = AuthUser(
            userName = registerInfo.userName,
            userEmail = registerInfo.userEmail,
            password = passwordEncoder.encode(registerInfo.password)
        )
        authUserRepository.save(user)
        return user
    }

    fun run(registerInfo: RegisterInfo): Map<String, String> {
        checkDuplicateUser(registerInfo)
        validatePassword(registerInfo.password, registerInfo.passwordAgain)
        val user = createUser(registerInfo)
        return jwtTokenProvider.generateToken(user)
    }
}