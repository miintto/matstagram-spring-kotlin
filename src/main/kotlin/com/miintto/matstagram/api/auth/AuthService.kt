package com.miintto.matstagram.api.auth

import com.miintto.matstagram.api.auth.dto.RegisterInfo
import com.miintto.matstagram.api.user.domain.AuthUser
import com.miintto.matstagram.api.user.domain.repository.AuthUserRepository
import com.miintto.matstagram.common.exception.APIException
import com.miintto.matstagram.common.response.code.Http4xx
import com.miintto.matstagram.common.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AuthService {

    @Autowired
    private lateinit var authUserRepository: AuthUserRepository

    @Autowired
    private lateinit var jwtTokenProvider: JwtTokenProvider

    private fun checkDuplicateUser(registerInfo: RegisterInfo) {
        if (authUserRepository.existsByUserName(registerInfo.userName)) {
            throw APIException(Http4xx.BAD_REQUEST, "중복된 닉네임입니다.")
        } else if (authUserRepository.existsByUserEmail(registerInfo.userEmail)) {
            throw APIException(Http4xx.BAD_REQUEST, "중복된 이메일입니다.")
        }
    }

    private fun createUser(registerInfo: RegisterInfo): AuthUser {
        val user = AuthUser(
            userName = registerInfo.userName,
            userEmail = registerInfo.userEmail,
        )
        user.setPassword(registerInfo.password, registerInfo.passwordAgain)
        authUserRepository.save(user)
        return user
    }

    fun register(registerInfo: RegisterInfo): Map<String, String> {
        checkDuplicateUser(registerInfo)
        val user = createUser(registerInfo)
        return jwtTokenProvider.generateToken(user)
    }
}
