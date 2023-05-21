package com.miintto.matstagram.common.response

import com.miintto.matstagram.common.response.code.ResponseFormat
import org.springframework.http.ResponseEntity

class APIResponse(format: ResponseFormat, data: Any?) : ResponseEntity<ResponseDto>(ResponseDto(format.code, format.message, data), format.status) {

    constructor(format: ResponseFormat) : this(format, null)
}
