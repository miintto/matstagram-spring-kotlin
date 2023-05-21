package com.miintto.matstagram.common.exception

import com.miintto.matstagram.common.response.APIResponse
import com.miintto.matstagram.common.response.code.Http5xx
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class APIExceptionHandler {
    @ExceptionHandler(value = [Exception::class])
    fun exception(e: Exception) : APIResponse {
        return APIResponse(Http5xx.SERVER_ERROR)
    }

    @ExceptionHandler(value = [APIException::class])
    fun exception(e: APIException) : APIResponse {
        return APIResponse(e.responseFormat, e.data)
    }
}
