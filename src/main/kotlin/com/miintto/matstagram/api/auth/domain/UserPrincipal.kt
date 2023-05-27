package com.miintto.matstagram.api.auth.domain

import com.miintto.matstagram.api.user.domain.AuthUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal(private val user: AuthUser) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList()
    }

    override fun getPassword() = this.user.password

    override fun getUsername() = this.user.userEmail

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}
