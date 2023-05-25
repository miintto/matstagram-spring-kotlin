package com.miintto.matstagram.api.user.domain

import com.miintto.matstagram.common.exception.APIException
import com.miintto.matstagram.common.response.code.Http4xx
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
    ADMIN,
}

@Entity
@Table(name = "t_auth_user")
data class AuthUser (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var userName: String,

    var userEmail: String,

    var password: String? = null,

    @Enumerated(EnumType.STRING)
    var userPermission: UserPermission = UserPermission.NORMAL,

    var isActive: Boolean = true,

    var profileImage: String? = null,

    var createdDtm: LocalDateTime = LocalDateTime.now(),

    var lastLoginDtm: LocalDateTime? = null,
) {
    fun setPassword(password1: String, password2: String) {
        if (password1 != password2) {
            throw APIException(Http4xx.INVALID_PASSWORD, "비밀번호가 서로 일치하지 않습니다.")
        }
        this.password = password1
    }
}
