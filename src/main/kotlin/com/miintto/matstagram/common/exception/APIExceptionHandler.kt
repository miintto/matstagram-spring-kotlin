package com.miintto.matstagram.common.exception

import com.miintto.matstagram.common.response.APIResponse
import com.miintto.matstagram.common.response.code.Http4xx
import com.miintto.matstagram.common.response.code.Http5xx
import mu.KotlinLogging
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class APIExceptionHandler {
    @ExceptionHandler(value = [Exception::class])
    fun handleException(e: Exception): APIResponse {
        logger.error(e.message, e)
        return APIResponse(Http5xx.SERVER_ERROR)
    }

    @ExceptionHandler(value = [AuthenticationException::class])
    fun handleAuthError(e: AuthenticationException): APIResponse {
        logger.error(e.message)
        return APIResponse(Http4xx.UNAUTHENTICATED)
    }

    @ExceptionHandler(value = [HttpMessageNotReadableException::class])
    fun handleParamsError(e: Exception): APIResponse {
        logger.error(e.message)
        return APIResponse(Http4xx.BAD_REQUEST)
    }

    @ExceptionHandler(value = [APIException::class])
    fun handleApiException(e: APIException): APIResponse {
        logger.error(e.toString())
        return APIResponse(e.responseFormat, e.data)
    }
}
