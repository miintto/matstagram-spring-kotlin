package com.miintto.matstagram.api.auth.service

import com.miintto.matstagram.api.auth.dto.LoginInfo
import com.miintto.matstagram.api.user.domain.AuthUser
import com.miintto.matstagram.api.user.domain.repository.AuthUserRepository
import com.miintto.matstagram.common.exception.APIException
import com.miintto.matstagram.common.response.code.Http4xx
import com.miintto.matstagram.common.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoginService {

    @Autowired
    private lateinit var authUserRepository: AuthUserRepository

    @Autowired
    private lateinit var jwtTokenProvider: JwtTokenProvider

    private fun getUser(userEmail: String): AuthUser {
        return authUserRepository.findByUserEmail(userEmail) ?: throw APIException(Http4xx.USER_NOT_FOUND)
    }

    fun run(loginInfo: LoginInfo): Map<String, String> {
        val user = getUser(loginInfo.userEmail)
        return jwtTokenProvider.generateToken(user)
    }
}
