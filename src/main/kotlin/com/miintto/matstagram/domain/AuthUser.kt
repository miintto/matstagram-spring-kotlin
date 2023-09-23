package com.miintto.matstagram.domain

import net.bytebuddy.build.ToStringPlugin.Exclude
import java.time.LocalDateTime
import javax.persistence.Enumerated
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

enum class UserPermission {
    ANONYMOUS,
    NORMAL,
    ADMIN
}

@Entity
@Table(name = "t_auth_user")
class AuthUser(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var userName: String,

    var userEmail: String,

    @Exclude
    var password: String? = null,

    @Enumerated(EnumType.STRING)
    var userPermission: UserPermission = UserPermission.NORMAL,

    var isActive: Boolean = true,

    var profileImage: String? = null,

    var createdDtm: LocalDateTime = LocalDateTime.now(),

    var lastLoginDtm: LocalDateTime? = null
)
