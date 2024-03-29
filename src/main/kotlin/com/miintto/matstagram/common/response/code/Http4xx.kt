package com.miintto.matstagram.common.response.code

import org.springframework.http.HttpStatus

enum class Http4xx(
    override val code: String,
    override val message: String,
    override val status: HttpStatus
) : BaseHttp {

    BAD_REQUEST("F000", "잘못된 요청", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED("F001", "잘못된 인증 정보입니다.", HttpStatus.UNAUTHORIZED),
    PERMISSION_DENIED("F002", "권한이 없습니다.", HttpStatus.FORBIDDEN),
    USER_NOT_FOUND("F003", "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INVALID_PASSWORD("F004", "유효하지 않은 비밀번호입니다.", HttpStatus.UNPROCESSABLE_ENTITY),
    PLACE_NOT_FOUND("F005", "장소를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    TAG_NOT_FOUND("F006", "태그를 찾을 수 없습니다.", HttpStatus.NOT_FOUND)
}
