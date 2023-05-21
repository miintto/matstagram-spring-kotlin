package com.miintto.matstagram.api.user

import com.miintto.matstagram.api.user.domain.AuthUser
import com.miintto.matstagram.api.user.domain.repository.AuthUserRepository
import com.miintto.matstagram.common.exception.APIException
import com.miintto.matstagram.common.response.code.Http4xx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserService {

    @Autowired
    lateinit var authUserRepository: AuthUserRepository

    fun getUserById(userId: Long): AuthUser {
        return authUserRepository.findById(userId) ?: throw APIException(Http4xx.USER_NOT_FOUND)
    }
}
