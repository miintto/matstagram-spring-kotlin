package com.miintto.matstagram.common.response.code

import org.springframework.http.HttpStatus

enum class Http4xx(
    override val code: String,
    override val message: String,
    override val status: HttpStatus,
) : ResponseFormat {

    BAD_REQUEST("F001", "에러", HttpStatus.BAD_REQUEST),
}