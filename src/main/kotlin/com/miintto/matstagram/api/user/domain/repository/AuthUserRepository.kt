package com.miintto.matstagram.api.user.domain.repository

import com.miintto.matstagram.api.user.domain.AuthUser
import org.springframework.data.repository.CrudRepository

interface AuthUserRepository : CrudRepository<AuthUser, String> {

    fun findById(userId: Long): AuthUser?
}
