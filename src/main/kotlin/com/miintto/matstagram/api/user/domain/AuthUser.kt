package com.miintto.matstagram.api.user.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "t_auth_user")
data class AuthUser (

    @Id
    @Column
    var id: Long = 0,

    var userName: String,
    var userEmail: String,
    var userPermission: String,
    var isActive: Boolean,
    var profileImage: String,
    var createdDtm: LocalDateTime,
    var lastLoginDtm: LocalDateTime,
)
