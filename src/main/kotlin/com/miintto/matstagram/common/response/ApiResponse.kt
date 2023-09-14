package com.miintto.matstagram.common.response

import com.miintto.matstagram.common.response.code.BaseHttp
import org.springframework.http.ResponseEntity

class ApiResponse(http: BaseHttp, data: Any?) : ResponseEntity<ResponseDto>(
    ResponseDto(
        http.code,
        http.message,
        data
    ),
    http.status
) {

    constructor(http: BaseHttp) : this(http, null)
}
