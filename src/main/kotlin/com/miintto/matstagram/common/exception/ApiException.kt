package com.miintto.matstagram.common.exception

import com.miintto.matstagram.common.response.code.BaseHttp

class ApiException(val http: BaseHttp, val data: Any?) : Exception(http.message) {

    constructor(http: BaseHttp) : this(http, null)

    override fun toString(): String {
        return "ApiException { code=${this.http.code}, message=${this.message}, status=${this.http.status} }"
    }
}
