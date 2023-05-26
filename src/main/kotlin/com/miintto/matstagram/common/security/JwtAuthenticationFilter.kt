package com.miintto.matstagram.common.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class JwtAuthenticationFilter : GenericFilterBean(){

    @Autowired
    private lateinit var jwtTokenProvider: JwtTokenProvider

    private fun resolveToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization") ?: return null
        val authArray = token.split(" ")
        if (authArray.size != 2) {
            throw Exception()
        } else if (authArray[0] != "JWT") {
            throw Exception()
        }
        return authArray[1]
    }

    private fun setAuthorization(token: String) {
        SecurityContextHolder.getContext().authentication = jwtTokenProvider.getAuthentication(token)
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val token = resolveToken(request as HttpServletRequest)
        if (token != null && jwtTokenProvider.validateToken(token)) {
            setAuthorization(token)
        }
        chain?.doFilter(request, response)
    }
}