package com.miintto.matstagram.api.user

import com.miintto.matstagram.api.user.domain.AuthUser
import com.miintto.matstagram.api.user.domain.repository.AuthUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserService {

    @Autowired
    lateinit var authUserRepository: AuthUserRepository

    fun getUserById(userPk: Long): AuthUser {
        return authUserRepository.findById(userPk) ?: throw IllegalArgumentException("No User!")
    }
}