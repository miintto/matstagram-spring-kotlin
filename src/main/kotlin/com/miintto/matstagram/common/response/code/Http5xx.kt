package com.miintto.matstagram.common.response.code

import org.springframework.http.HttpStatus

enum class Http5xx(
    override val code: String,
    override val message: String,
    override val status: HttpStatus,
) : BaseHttp {

    SERVER_ERROR("E001", "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
}
