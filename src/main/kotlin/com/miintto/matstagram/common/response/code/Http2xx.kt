package com.miintto.matstagram.common.response.code

import org.springframework.http.HttpStatus

enum class Http2xx(
    override val code: String,
    override val message: String,
    override val status: HttpStatus
) : BaseHttp {

    SUCCESS("S001", "성공", HttpStatus.OK),
    CREATED("S002", "생성 완료", HttpStatus.CREATED)
}
