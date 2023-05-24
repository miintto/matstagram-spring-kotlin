package com.miintto.matstagram.common.exception

import com.miintto.matstagram.common.response.code.ResponseFormat

class APIException(val responseFormat: ResponseFormat, val data: Any?) : Exception(responseFormat.message) {

    constructor(format: ResponseFormat) : this(format, null)

    override fun toString(): String {
        return "APIException { code=${this.responseFormat.code}, message=${this.message}, status=${this.responseFormat.status} }"
    }
}
