package com.miintto.matstagram.common.response

data class ResponseDto(
    val code: String,
    val message: String,
    val data: Any?
)
