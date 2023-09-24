package com.miintto.matstagram.domain.repository

import com.miintto.matstagram.domain.AuthUser
import com.miintto.matstagram.domain.projection.AuthUserSummary
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AuthUserRepository : JpaRepository<AuthUser, Long> {

    @Query("SELECT u FROM AuthUser u WHERE u.id = :id")
    fun findProjectedById(id: Long): AuthUserSummary?

    fun findByUserEmail(userEmail: String): AuthUser?

    fun existsByUserName(userName: String): Boolean

    fun existsByUserEmail(userEmail: String): Boolean
}
