package com.miintto.matstagram.common.exception

import com.miintto.matstagram.common.response.APIResponse
import com.miintto.matstagram.common.response.code.Http5xx
import mu.KotlinLogging
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

private val logger = KotlinLogging.logger {}

@ControllerAdvice
class APIExceptionHandler {
    @ExceptionHandler(value = [Exception::class])
    fun exception(e: Exception) : APIResponse {
        logger.error(e.message, e)
        return APIResponse(Http5xx.SERVER_ERROR)
    }

    @ExceptionHandler(value = [APIException::class])
    fun exception(e: APIException) : APIResponse {
        logger.error(e.toString())
        return APIResponse(e.responseFormat, e.data)
    }
}
