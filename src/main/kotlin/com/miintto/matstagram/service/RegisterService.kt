package com.miintto.matstagram.service

import com.miintto.matstagram.dto.RegisterInfo
import com.miintto.matstagram.domain.AuthUser
import com.miintto.matstagram.domain.repository.AuthUserRepository
import com.miintto.matstagram.common.exception.ApiException
import com.miintto.matstagram.common.response.code.Http4xx
import com.miintto.matstagram.common.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class RegisterService {

    @Autowired
    private lateinit var authUserRepository: AuthUserRepository

    @Autowired
    private lateinit var jwtTokenProvider: JwtTokenProvider

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    private fun checkDuplicateUser(registerInfo: RegisterInfo) {
        if (authUserRepository.existsByUserName(registerInfo.userName)) {
            throw ApiException(Http4xx.BAD_REQUEST, "중복된 닉네임입니다.")
        } else if (authUserRepository.existsByUserEmail(registerInfo.userEmail)) {
            throw ApiException(Http4xx.BAD_REQUEST, "중복된 이메일입니다.")
        }
    }

    private fun validatePassword(password1: String, password2: String) {
        if (password1 != password2) {
            throw ApiException(Http4xx.INVALID_PASSWORD, "비밀번호가 서로 일치하지 않습니다.")
        }
    }

    private fun createUser(registerInfo: RegisterInfo): AuthUser {
        return authUserRepository.save(
            AuthUser(
                userName = registerInfo.userName,
                userEmail = registerInfo.userEmail,
                password = passwordEncoder.encode(registerInfo.password)
            )
        )
    }

    fun run(registerInfo: RegisterInfo): Map<String, String> {
        checkDuplicateUser(registerInfo)
        validatePassword(registerInfo.password, registerInfo.passwordAgain)
        val user = createUser(registerInfo)
        return jwtTokenProvider.generateToken(user)
    }
}
