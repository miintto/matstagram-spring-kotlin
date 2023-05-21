package com.miintto.matstagram.common.response

import org.springframework.http.HttpStatus

enum class ResponseCode(val message: String, val status: HttpStatus) {
    S001("성공", HttpStatus.OK),
    S002("생성 완료", HttpStatus.CREATED),
}