package com.miintto.matstagram.common.exception

import com.miintto.matstagram.common.response.ApiResponse
import com.miintto.matstagram.common.response.code.Http4xx
import com.miintto.matstagram.common.response.code.Http5xx
import mu.KotlinLogging
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class ApiExceptionHandler {
    @ExceptionHandler(value = [Exception::class])
    fun handleException(e: Exception): ApiResponse {
        logger.error(e.message, e)
        return ApiResponse(Http5xx.SERVER_ERROR)
    }

    @ExceptionHandler(value = [AuthenticationException::class])
    fun handleAuthError(e: AuthenticationException): ApiResponse {
        logger.error(e.message)
        return ApiResponse(Http4xx.UNAUTHENTICATED)
    }

    @ExceptionHandler(value = [HttpMessageNotReadableException::class])
    fun handleParamsError(e: Exception): ApiResponse {
        logger.error(e.message)
        return ApiResponse(Http4xx.BAD_REQUEST)
    }

    @ExceptionHandler(value = [ApiException::class])
    fun handleApiException(e: ApiException): ApiResponse {
        logger.error(e.toString())
        return ApiResponse(e.responseFormat, e.data)
    }
}
