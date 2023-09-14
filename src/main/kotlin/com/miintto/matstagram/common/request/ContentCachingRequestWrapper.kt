package com.miintto.matstagram.common.request

import org.apache.tomcat.util.http.fileupload.IOUtils
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.lang.RuntimeException
import javax.servlet.ReadListener
import javax.servlet.ServletInputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper

class ContentCachingRequestWrapper(request: HttpServletRequest) : HttpServletRequestWrapper(request) {
    private var content = ByteArrayOutputStream()

    override fun getInputStream(): ServletInputStream {
        IOUtils.copy(super.getInputStream(), content)

        return object : ServletInputStream() {
            private var buffer = ByteArrayInputStream(content.toByteArray())
            override fun read(): Int = buffer.read()
            override fun isFinished(): Boolean = buffer.available() == 0
            override fun isReady(): Boolean = true
            override fun setReadListener(listener: ReadListener?) = throw RuntimeException("Not implemented")
        }
    }

    val contentAsByteArray: ByteArray
        get() = this.inputStream.readAllBytes()
}
