package com.miintto.matstagram.common.response

import org.springframework.http.ResponseEntity

data class ResponseDto(
    val code: String,
    val message: String,
    val data: Any?,
)

class APIResponse(code: ResponseCode, data: Any?) : ResponseEntity<ResponseDto>(ResponseDto(code.name, code.message, data), code.status) {

    constructor(code: ResponseCode) : this(code, null)
}