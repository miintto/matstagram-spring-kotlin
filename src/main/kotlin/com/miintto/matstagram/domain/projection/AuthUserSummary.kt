package com.miintto.matstagram.domain.projection

import java.time.LocalDateTime

interface AuthUserSummary {

    val id: Long

    val userName: String

    val userEmail: String

    val userPermission: String

    val isActive: Boolean

    val profileImage: String?

    val createdDtm: LocalDateTime

    val lastLoginDtm: LocalDateTime?
}
