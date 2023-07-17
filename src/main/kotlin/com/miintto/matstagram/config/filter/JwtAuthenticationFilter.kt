package com.miintto.matstagram.config.filter

import com.miintto.matstagram.common.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var jwtTokenProvider: JwtTokenProvider

    private fun resolveToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization") ?: return null
        val authArray = token.split(" ")
        if (authArray.size != 2) {
            return null
        } else if (authArray[0] != "JWT") {
            return null
        }
        return authArray[1]
    }

    private fun setAuthorization(token: String) {
        SecurityContextHolder.getContext().authentication = jwtTokenProvider.getAuthentication(token)
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolveToken(request)
        if (token != null && jwtTokenProvider.validateToken(token)) {
            setAuthorization(token)
        }
        filterChain.doFilter(request, response)
    }
}