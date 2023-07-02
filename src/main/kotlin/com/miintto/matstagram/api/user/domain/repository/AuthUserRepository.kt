package com.miintto.matstagram.api.user.domain.repository

import com.miintto.matstagram.api.user.domain.AuthUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthUserRepository : JpaRepository<AuthUser, Long> {

    fun findByUserEmail(userEmail: String): AuthUser?

    fun existsByUserName(userName: String): Boolean

    fun existsByUserEmail(userEmail: String): Boolean
}
