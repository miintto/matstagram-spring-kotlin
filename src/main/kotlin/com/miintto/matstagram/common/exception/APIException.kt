package com.miintto.matstagram.common.exception

import com.miintto.matstagram.common.response.code.ResponseFormat

class APIException(val responseFormat: ResponseFormat, val data: Any?) : Exception(responseFormat.message) {

    constructor(format: ResponseFormat) : this(format, null)
}
