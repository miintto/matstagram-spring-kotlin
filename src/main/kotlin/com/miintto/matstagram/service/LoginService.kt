package com.miintto.matstagram.service

import com.miintto.matstagram.dto.LoginInfo
import com.miintto.matstagram.domain.AuthUser
import com.miintto.matstagram.domain.repository.AuthUserRepository
import com.miintto.matstagram.common.exception.ApiException
import com.miintto.matstagram.common.response.code.Http4xx
import com.miintto.matstagram.common.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginService {

    @Autowired
    private lateinit var authUserRepository: AuthUserRepository

    @Autowired
    private lateinit var jwtTokenProvider: JwtTokenProvider

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    private fun getUser(userEmail: String): AuthUser {
        return authUserRepository.findByUserEmail(userEmail) ?: throw ApiException(Http4xx.USER_NOT_FOUND)
    }

    private fun checkPassword(rawPassword: String, encodedPassword: String) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw ApiException(Http4xx.INVALID_PASSWORD)
        }
    }

    fun run(loginInfo: LoginInfo): Map<String, String> {
        val user = getUser(loginInfo.userEmail)
        checkPassword(loginInfo.password, user.password!!)
        return jwtTokenProvider.generateToken(user)
    }
}
