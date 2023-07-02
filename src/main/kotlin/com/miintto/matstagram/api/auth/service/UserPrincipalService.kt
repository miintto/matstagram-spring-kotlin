package com.miintto.matstagram.api.auth.service

import com.miintto.matstagram.api.auth.domain.UserPrincipal
import com.miintto.matstagram.api.user.domain.repository.AuthUserRepository
import com.miintto.matstagram.common.exception.ApiException
import com.miintto.matstagram.common.response.code.Http4xx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserPrincipalService : UserDetailsService {

    @Autowired
    private lateinit var userRepository: AuthUserRepository

    override fun loadUserByUsername(userId: String): UserDetails {
        val user = userRepository.findByIdOrNull(userId.toLong()) ?: throw ApiException(Http4xx.UNAUTHENTICATED)
        return UserPrincipal(user)
    }
}
