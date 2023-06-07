package com.miintto.matstagram.common.exception

import com.miintto.matstagram.common.response.code.BaseHttp

class ApiException(val responseFormat: BaseHttp, val data: Any?) : Exception(responseFormat.message) {

    constructor(format: BaseHttp) : this(format, null)

    override fun toString(): String {
        return "ApiException { code=${this.responseFormat.code}, message=${this.message}, status=${this.responseFormat.status} }"
    }
}
