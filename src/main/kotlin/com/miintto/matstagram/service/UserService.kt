package com.miintto.matstagram.service

import com.miintto.matstagram.domain.AuthUser
import com.miintto.matstagram.domain.repository.AuthUserRepository
import com.miintto.matstagram.common.exception.ApiException
import com.miintto.matstagram.common.response.code.Http4xx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var authUserRepository: AuthUserRepository

    fun getUserById(userId: Long): AuthUser {
        return authUserRepository.findByIdOrNull(userId)
            ?: throw ApiException(Http4xx.USER_NOT_FOUND)
    }
}
