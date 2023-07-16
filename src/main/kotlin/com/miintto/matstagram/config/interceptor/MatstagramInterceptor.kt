package com.miintto.matstagram.config.interceptor

import com.miintto.matstagram.common.request.ContentCachingRequestWrapper
import mu.KotlinLogging
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.util.ContentCachingResponseWrapper
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

    private fun loggingRequestBody(request: ContentCachingRequestWrapper) {
        val requestBody = String(request.contentAsByteArray, Charsets.UTF_8)
        if (requestBody.isNotBlank()) {
            logger.info("Request Body - $requestBody")
        }
    }

    private fun loggingResponseBody(response: ContentCachingResponseWrapper) {
        val contentType = response.contentType
        if (contentType != null && contentType.contains("application/json")) {
            val responseBody = String(response.contentAsByteArray, Charsets.UTF_8)
            logger.info("Response Body - $responseBody")
        }
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.info( "Request ${getRequestURI(request)}")
        loggingRequestBody(request as ContentCachingRequestWrapper)
        return super.preHandle(request, response, handler)
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        logger.info( "Response ${getRequestURI(request)} - ${response.status}")
        loggingResponseBody(response as ContentCachingResponseWrapper)
        super.afterCompletion(request, response, handler, ex)
    }
}
