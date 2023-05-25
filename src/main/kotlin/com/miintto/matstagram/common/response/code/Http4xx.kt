package com.miintto.matstagram.common.response.code

import org.springframework.http.HttpStatus

enum class Http4xx(
    override val code: String,
    override val message: String,
    override val status: HttpStatus,
) : ResponseFormat {

    BAD_REQUEST("F001", "잘못된 요청", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("F002", "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
}
