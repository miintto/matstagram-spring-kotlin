package com.miintto.matstagram.config

import mu.KotlinLogging
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MatstagramInterceptor: HandlerInterceptor {

    private val logger = KotlinLogging.logger {}

    private fun getRequestURI(request: HttpServletRequest): String {
        return if (request.queryString != null) {
            "${request.method} ${request.requestURI}?${request.queryString}"
        } else {
            "${request.method} ${request.requestURI}"
        }
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.info( "Request ${getRequestURI(request)}")
        return super.preHandle(request, response, handler)
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        logger.info( "Response ${getRequestURI(request)} - ${response.status}")
        super.afterCompletion(request, response, handler, ex)
    }
}
