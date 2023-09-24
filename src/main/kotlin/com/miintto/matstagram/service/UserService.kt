package com.miintto.matstagram.service

import com.miintto.matstagram.domain.repository.AuthUserRepository
import com.miintto.matstagram.common.exception.ApiException
import com.miintto.matstagram.common.response.code.Http4xx
import com.miintto.matstagram.domain.projection.AuthUserSummary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var authUserRepository: AuthUserRepository

    fun getUserById(userId: Long): AuthUserSummary {
        return authUserRepository.findProjectedById(userId)
            ?: throw ApiException(Http4xx.USER_NOT_FOUND)
    }
}
