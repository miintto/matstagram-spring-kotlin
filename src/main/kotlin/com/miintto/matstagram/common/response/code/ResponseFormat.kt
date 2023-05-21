package com.miintto.matstagram.common.response.code

import org.springframework.http.HttpStatus

interface ResponseFormat{
    val code: String
    val message: String
    val status: HttpStatus
}
